package managedbeans;

import entities.Jerarquia;
import entities.GradoAcademico;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.JerarquiaFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("jerarquiaController")
@SessionScoped
public class JerarquiaController implements Serializable {

    @EJB
    private JerarquiaFacadeLocal ejbFacade;
    private List<Jerarquia> items = null;
    private Jerarquia selected;
    private List<Jerarquia> lista_nombres = null;
    

    public JerarquiaController() {
    }

    
      
    public Jerarquia getSelected() {
        return selected;
    }

    public void setSelected(Jerarquia selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private JerarquiaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Jerarquia prepareCreate() {
        selected = new Jerarquia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
       FacesContext context = FacesContext.getCurrentInstance();
        lista_nombres = ejbFacade.findByNombrelist(selected.getNombre());
        if(lista_nombres.isEmpty()){
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("JerarquiaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jerarquía ya existe", null));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("JerarquiaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("JerarquiaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Jerarquia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Jerarquia getJerarquia(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Jerarquia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Jerarquia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    
    
    @FacesConverter(forClass = Jerarquia.class)
    public static class JerarquiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            JerarquiaController controller = (JerarquiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "jerarquiaController");
            return controller.getJerarquia(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Jerarquia) {
                Jerarquia o = (Jerarquia) object;
                return getStringKey(o.getId_jerarquia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Jerarquia.class.getName()});
                return null;
            }
        }

    }

}
