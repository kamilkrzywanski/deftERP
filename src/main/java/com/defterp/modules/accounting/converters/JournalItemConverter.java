//package com.defterp.modules.accounting.converters;
//
//import com.defterp.modules.accounting.entities.JournalItem;
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
//@FacesConverter(value = "journalItemConverter")
//public class JournalItemConverter implements Converter {
//
//    @Inject
//    private GenericDAO dataAccess;
//
//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
//            return null;
//        }
//        return dataAccess.findById(getKey(value), JournalItem.class);
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
//        if (object == null ) {
//            return null;
//        }
//        if (object instanceof JournalItem) {
//            JournalItem o = (JournalItem) object;
//            return getStringKey(o.getId());
//        } else {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), JournalItem.class.getName()});
//            return null;
//        }
//    }
//
//}
