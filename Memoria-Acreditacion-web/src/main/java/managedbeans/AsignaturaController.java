package managedbeans;

import entities.Asignatura;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.AsignaturaFacadeLocal;

import java.io.Serializable;
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

@Named("asignaturaController")
@SessionScoped
public class AsignaturaController implements Serializable {

    @EJB
    private AsignaturaFacadeLocal ejbFacade;
    private List<Asignatura> items = null;
    private Asignatura selected;
    private List<Asignatura> lista_nombres = null;
    

    public AsignaturaController() {
    }

    public Asignatura getSelected() {
        return selected;
    }

    public void setSelected(Asignatura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsignaturaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Asignatura prepareCreate() {
        selected = new Asignatura();
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
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Asignatura ya existe", null));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asignatura> getItems() {
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

    public Asignatura getAsignatura(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Asignatura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asignatura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Asignatura.class)
    public static class AsignaturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignaturaController controller = (AsignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignaturaController");
            return controller.getAsignatura(getKey(value));
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
            if (object instanceof Asignatura) {
                Asignatura o = (Asignatura) object;
                return getStringKey(o.getId_asignatura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asignatura.class.getName()});
                return null;
            }
        }

    }

}
