package com.defterp.modules.inventory.converters;

import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.inventory.entities.ProductUomCategory;
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
@Named("productUomCategoryConverter")
@ApplicationScoped
public class ProductUomCategoryConverter implements Converter<ProductUomCategory> {

    @Inject
    private GenericDAO dataAccess;

    @Override
    public ProductUomCategory getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return dataAccess.findById(getKey(value), ProductUomCategory.class);
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
    public String getAsString(FacesContext facesContext, UIComponent component, ProductUomCategory object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ProductUomCategory) {
            ProductUomCategory o = (ProductUomCategory) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProductUomCategory.class.getName()});
            return null;
        }
    }

}
