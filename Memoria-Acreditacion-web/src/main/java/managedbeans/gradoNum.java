package managedbeans;

import entities.GradoAcademico;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("gradoNum")
@SessionScoped
public class gradoNum implements Serializable {

    String grado;
    int cant_prof1;
    int cant_prof2;
    int cant_prof3;
    
    public gradoNum(){
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getCant_prof1() {
        return cant_prof1;
    }

    public void setCant_prof1(int cant_prof1) {
        this.cant_prof1 = cant_prof1;
    }

    public int getCant_prof2() {
        return cant_prof2;
    }

    public void setCant_prof2(int cant_prof2) {
        this.cant_prof2 = cant_prof2;
    }

    public int getCant_prof3() {
        return cant_prof3;
    }

    public void setCant_prof3(int cant_prof3) {
        this.cant_prof3 = cant_prof3;
    }

    
}
