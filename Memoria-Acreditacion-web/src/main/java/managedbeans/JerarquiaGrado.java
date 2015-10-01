package managedbeans;

import entities.GradoAcademico;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("JerarquiaGrado")
@SessionScoped
public class JerarquiaGrado implements Serializable {

    String jerarquia;
    int year1;
    int year2;
    int year3;

public JerarquiaGrado() {
    
    }

    public String getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(String jerarquia) {
        this.jerarquia = jerarquia;
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public int getYear2() {
        return year2;
    }

    public void setYear2(int year2) {
        this.year2 = year2;
    }

    public int getYear3() {
        return year3;
    }

    public void setYear3(int year3) {
        this.year3 = year3;
    }

    


}