package managedbeans;

import entities.Profesor;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("edad")
@SessionScoped

public class edad implements Serializable {

public edad() {
    }  

    String rangos;
    int edad;
    int edad2;
    int edad3;

    public String getRangos() {
        return rangos;
    }

    public void setRangos(String rangos) {
        this.rangos = rangos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad2() {
        return edad2;
    }

    public void setEdad2(int edad2) {
        this.edad2 = edad2;
    }

    public int getEdad3() {
        return edad3;
    }

    public void setEdad3(int edad3) {
        this.edad3 = edad3;
    }
    
    
    
}
