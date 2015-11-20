/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import sessionbeans.UsuarioFacadeLocal;
import entities.Tipousuario;
import entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import managedbeans.util.JsfUtil;
import org.apache.http.HttpResponse;
import sessionbeans.UsuarioFacadeLocal;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.primefaces.json.JSONObject;

/**
 *
 * @author MacBookPro
 */
@Named(value = "loginController")
//@RequestScoped
@SessionScoped
public class LoginController implements Serializable{

    private String nombre;
    private String password;
    private boolean error = false;
    private String nombre_usuario;
    private List<Tipousuario> roles;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public List<Tipousuario> getRoles() {
        return roles;
    }

    public void setRoles(List<Tipousuario> roles) {
        this.roles = roles;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public LoginController(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    
    public void login(){
        System.out.println("Función login: Comenzando autenticación");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        //LoginContext lc = null;
        
        try {
            if(!hasIdentity()) {
                //Autenticación con LDAP
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://inicio.diinf.usach.cl/webservice.php");

                // Add your data
                List<BasicNameValuePair> nameValuePairs = new ArrayList<>(2);
                nameValuePairs.add(new BasicNameValuePair("user", nombre));
                nameValuePairs.add(new BasicNameValuePair("pass", password));
                nameValuePairs.add(new BasicNameValuePair("keyapi", MD5("c55ecd5c60a5a5b2bea1c92bbc45f8ab")));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

                String responseString = new BasicResponseHandler().handleResponse(response);
                System.out.println(responseString);
                
                /*
                JSONParser parser = new JSONParser(null, null)
                Object obj = parser.parse(responseString);
                */
                
                JSONObject jsonObject = new JSONObject(responseString);

                Boolean valido_response = (Boolean) jsonObject.get("pass_ok");
                if(valido_response == null) {
                    valido_response = false;
                }
                System.out.println("Datos Validos: " + valido_response);               
                
                //FIN AUTENTICACIÓN LDAP
                
                System.out.println(jsonObject.getString("pass_ok"));
                
                request.login(nombre, jsonObject.getString("pass_ok"));
                
                System.out.println("flag");
                
                if(valido_response){
                    System.out.println("SessionUtil: SessionScope created for " + nombre);
                    JsfUtil.addSuccessMessage("Logeado con éxito");
                    Usuario usuario = usuarioFacade.findByUsername(nombre);
                    nombre_usuario = usuario.getNombreUsuario();
                    setRoles(usuario.getRoles());
                    System.out.println("nombre de usuario: "+usuario.getNombreUsuario() +" - rol: "+usuario.getRoles().get(0).getNombreTipo());
                    for (int i = 0; i < usuario.getRoles().size(); i++) {
                        if(usuario.getRoles().get(i).getNombreTipo().equals("ADMINISTRADOR")){
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/Memoria-Acreditacion-web/faces/administrador/index1.xhtml");
                        }
                        if(usuario.getRoles().get(i).getNombreTipo().equals("COORDINADOR")){
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/Memoria-Acreditacion-web/faces/coordinador/index1.xhtml");
                        }
                        if(usuario.getRoles().get(i).getNombreTipo().equals("COMITE")){
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/Memoria-Acreditacion-web/faces/comite/index1.xhtml");
                        }
                    }
                    error = false;
                }
            } 
            else {
                System.out.println("SessionUtil: User allready logged");
                error = false;
            }
            
        } 
        catch (Exception e) {
            System.out.println("SessionUtil: User or password not found");
            JsfUtil.addErrorMessage("Usuario o contraseña no existe");
            error = true;
        }
    }
    
    public boolean hasIdentity(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        if(request.getRemoteUser() == null){
            return false;
        }
        return true;
    }
    
    public void logout() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        nombre_usuario = null;
        externalContext.redirect("/Memoria-Acreditacion-web/");
    }
    
    public boolean compruebaRoles(){
        boolean roles = false;
        if(getRoles().size() > 1){
            if(getRoles().get(0).getNombreTipo().equals("ADMINISTRADOR")){
                roles = true;
            }
        }
        return roles;
    }
}
