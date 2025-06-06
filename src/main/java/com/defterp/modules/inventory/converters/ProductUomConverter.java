package com.defterp.modules.inventory.converters;

import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.inventory.entities.ProductUom;
import com.defterp.util.JsfUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */
@Named("productUomConverter")
@ApplicationScoped
public class ProductUomConverter implements Converter<ProductUom> {

    @Inject
    private GenericDAO dataAccess;

    @Override
    public ProductUom getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return dataAccess.findById(getKey(value), ProductUom.class);
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
    public String getAsString(FacesContext facesContext, UIComponent component, ProductUom object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ProductUom) {
            ProductUom o = (ProductUom) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProductUom.class.getName()});
            return null;
        }
    }

}
