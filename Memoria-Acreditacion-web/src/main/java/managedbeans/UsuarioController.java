package managedbeans;

import entities.Rol;
import entities.Tipousuario;
import entities.Usuario;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.UsuarioFacadeLocal;
import java.io.Serializable;
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
import sessionbeans.RolFacadeLocal;
import sessionbeans.TipousuarioFacadeLocal;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private RolFacadeLocal rolFacade;
    
    @EJB
    private UsuarioFacadeLocal ejbFacade;
    @EJB
    private TipousuarioFacadeLocal tipoFacade;
    private List<Usuario> items = null;
    private Usuario selected;
    private List<Rol> roles = null;
    private List<Rol> roles_asignados = null;
    private Rol rol_add;
    private Rol rol_del;
    private Tipousuario tipos;

    public UsuarioController() {
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public Tipousuario getTipos() {
        return tipos;
    }
    
    public Boolean esAdmin(Usuario user){
        Tipousuario tipos = tipoFacade.findByName("ADMINISTRADOR");
        List <Usuario> usuarios = tipos.getUsuarioList();
        Boolean resp = usuarios.contains(user);
        return resp;
    }
    
    public Boolean esCoordinador(Usuario user){
        Tipousuario tipos = tipoFacade.findByName("COORDINADOR");
        List <Usuario> usuarios = tipos.getUsuarioList();
        Boolean resp = usuarios.contains(user);
        return resp;
    }
    
    public Boolean esComite(Usuario user){
        Tipousuario tipos = tipoFacade.findByName("COMITE");
        List <Usuario> usuarios = tipos.getUsuarioList();
        Boolean resp = usuarios.contains(user);
        return resp;
    }

    public void setTipos(Tipousuario tipos) {
        this.tipos = tipos;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacadeLocal getFacade() {
        return ejbFacade;
    }
    
    public TipousuarioFacadeLocal getTipoFacade() {
        return tipoFacade;
    }
    
    
    public void setAdmin(Usuario user){
        Tipousuario tipo = tipoFacade.findByName("ADMINISTRADOR");
        List <Usuario> usuarios = tipo.getUsuarioList();
        usuarios.add(user);
        tipoFacade.edit(tipo);
    }
    
    public void deleteAdmin(Usuario user){
        Tipousuario tipo = tipoFacade.findByName("ADMINISTRADOR");
        List <Usuario> usuarios = tipo.getUsuarioList();
        usuarios.remove(user);
        tipoFacade.edit(tipo);
    }
    
    public void setCoordinador(Usuario user){
        Tipousuario tipo = tipoFacade.findByName("COORDINADOR");
        List <Usuario> usuarios = tipo.getUsuarioList();
        usuarios.add(user);
        tipoFacade.edit(tipo);
    }
    
    public void deleteCoordinador(Usuario user){
        Tipousuario tipo = tipoFacade.findByName("COORDINADOR");
        List <Usuario> usuarios = tipo.getUsuarioList();
        usuarios.remove(user);
        tipoFacade.edit(tipo);
    }
    
    public void setComite(Usuario user){
        Tipousuario tipo = tipoFacade.findByName("COMITE");
        List <Usuario> usuarios = tipo.getUsuarioList();
        usuarios.add(user);
        tipoFacade.edit(tipo);
    }
    
    public void deleteComite(Usuario user){
        Tipousuario tipo = tipoFacade.findByName("COMITE");
        List <Usuario> usuarios = tipo.getUsuarioList();
        usuarios.remove(user);
        tipoFacade.edit(tipo);
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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

    public Usuario getUsuario(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    
        
    public Rol getRol_add() {
        return rol_add;
    }

    public void setRol_add(Rol rol_add) {
        this.rol_add = rol_add;
    }

    public Rol getRol_del() {
        return rol_del;
    }

    public List<Rol> getRoles() {
        roles = rolFacade.findAll();
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void setRol_del(Rol rol_del) {
        this.rol_del = rol_del;
    }
    
    public List<Rol> getRoles_asignados() {
        roles_asignados = selected.getRolList();
        return roles_asignados;
    }

    public void setRoles_asignados(List<Rol> roles_asignados) {
        this.roles_asignados = roles_asignados;
    }
    
    public void addRol(){

    rol_add.getUsuariorolList().add(selected);
    selected.getRolList().add(rol_add);
    rolFacade.edit(rol_add);
     ejbFacade.edit(selected);
    }
    
    public void delRol(){
        rol_del.getUsuariorolList().remove(selected);
        selected.getRolList().remove(rol_del);
        rolFacade.edit(rol_del);
       
    } 
    
    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getRutUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}
