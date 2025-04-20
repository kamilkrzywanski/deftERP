package com.defterp.modules.accounting.converters;

import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.accounting.entities.Account;
import com.defterp.util.JsfUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@Named(value = "accountConverter")
@ApplicationScoped
public class AccountConverter implements Converter<Account> {

    @Inject
    private GenericDAO dataAccess;

    @Override
    public Account getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return dataAccess.findById(getKey(value), Account.class);
    }

    private Integer getKey(String value) {
        Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    private String getStringKey(Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Account object) {
        if (object == null) {
            return null;
        }
        Account o = (Account) object;
        return getStringKey(o.getId());
    }

}
