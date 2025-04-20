package com.defterp.modules.inventory.converters;

import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.inventory.entities.ProductCategory;
import com.defterp.util.JsfUtil;
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

@Named("productCategoryConverter")
public class ProductCategoryConverter implements Converter<ProductCategory> {

    @Inject
    private GenericDAO dataAccess;

    @Override
    public ProductCategory getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return dataAccess.findById(getKey(value), ProductCategory.class);
    }

    private Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    private String getStringKey(Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, ProductCategory object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ProductCategory) {
            ProductCategory o = (ProductCategory) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProductCategory.class.getName()});
            return null;
        }
    }

}
