package managedbeans;

import entities.Profesor;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("ContratoNumHora")
@SessionScoped
public class ContratoNumHora implements Serializable {
    
    float horast1;
    float horast2;
    float horast3;
    String nombre;
      
    public ContratoNumHora() {
    
    }

    public float getHorast1() {
        return horast1;
    }

    public void setHorast1(float horast1) {
        this.horast1 = horast1;
    }

    public float getHorast2() {
        return horast2;
    }

    public void setHorast2(float horast2) {
        this.horast2 = horast2;
    }

    public float getHorast3() {
        return horast3;
    }

    public void setHorast3(float horast3) {
        this.horast3 = horast3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}