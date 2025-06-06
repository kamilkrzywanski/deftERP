package com.defterp.modules.sales.converters;

import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.sales.entities.SaleOrder;
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

@FacesConverter(value = "saleOrderConverter")
public class SaleOrderConverter implements Converter<SaleOrder> {

    @Inject
    private GenericDAO dataAccess;

    @Override
    public SaleOrder getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.isEmpty() || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return dataAccess.findById(getKey(value), SaleOrder.class);
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
    public String getAsString(FacesContext facesContext, UIComponent component, SaleOrder object) {
        if (object == null) {
            return null;
        }
        if (object instanceof SaleOrder) {
            SaleOrder o = (SaleOrder) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SaleOrder.class.getName()});
            return null;
        }
    }

}
