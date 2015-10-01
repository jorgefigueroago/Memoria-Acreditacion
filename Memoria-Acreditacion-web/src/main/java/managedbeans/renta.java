package managedbeans;

import entities.GradoAcademico;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("renta")
@SessionScoped
public class renta implements Serializable {

    String nombre;
    double renta1;
    double renta2;
    double renta3;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getRenta1() {
        return renta1;
    }

    public void setRenta1(double renta1) {
        this.renta1 = renta1;
    }

    public double getRenta2() {
        return renta2;
    }

    public void setRenta2(double renta2) {
        this.renta2 = renta2;
    }

    public double getRenta3() {
        return renta3;
    }

    public void setRenta3(double renta3) {
        this.renta3 = renta3;
    }

    


    
    
    
    

}
