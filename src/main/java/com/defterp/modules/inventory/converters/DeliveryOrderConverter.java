//package com.defterp.modules.inventory.converters;
//
//import com.defterp.modules.inventory.entities.DeliveryOrder;
//import com.defterp.dataAccess.GenericDAO;
//import com.defterp.util.JsfUtil;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import jakarta.inject.Inject;
//import jakarta.faces.component.UIComponent;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.convert.Converter;
//import jakarta.faces.convert.FacesConverter;
//
/// **
// *
// * @author MOHAMMED BOUNAGA
// *
// * github.com/medbounaga
// */
//
//@FacesConverter(value = "deliveryOrderConverter")
//public class DeliveryOrderConverter implements Converter {
//
//    @Inject
//    private GenericDAO dataAccess;
//
//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
//            return null;
//        }
//        return dataAccess.findById(getKey(value), DeliveryOrder.class);
//    }
//
//    private Integer getKey(String value) {
//        Integer key;
//        key = Integer.valueOf(value);
//        return key;
//    }
//
//    private String getStringKey(Integer value) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(value);
//        return sb.toString();
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//        if (object == null
//                ) {
//            return null;
//        }
//        if (object instanceof DeliveryOrder) {
//            DeliveryOrder o = (DeliveryOrder) object;
//            return getStringKey(o.getId());
//        } else {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DeliveryOrder.class.getName()});
//            return null;
//        }
//    }
//
//}
