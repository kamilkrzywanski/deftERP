package com.defterp.modules.users.converters;

import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.users.entities.User;
import com.defterp.util.JsfUtil;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter<User> {

    @Inject
    private GenericDAO dataAccess;

    @Override
    public User getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return dataAccess.findById(getKey(value), User.class);
    }

    private Integer getKey(String value) {
        Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    private String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, User object) {
        if (object == null) {
            return null;
        }
        if (object instanceof User) {
            User o = (User) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
            return null;
        }
    }

}
