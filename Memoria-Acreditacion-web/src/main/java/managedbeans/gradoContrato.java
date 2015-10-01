package managedbeans;

import entities.GradoAcademico;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("gradoContrato")
@SessionScoped
public class gradoContrato implements Serializable {

    String grado;
    int contratoC;
    int contratoP;
    int contratoH;
    
    public gradoContrato(){
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    

    public int getContratoC() {
        return contratoC;
    }

    public void setContratoC(int contratoC) {
        this.contratoC = contratoC;
    }

    public int getContratoP() {
        return contratoP;
    }

    public void setContratoP(int contratoP) {
        this.contratoP = contratoP;
    }

    public int getContratoH() {
        return contratoH;
    }

    public void setContratoH(int contratoH) {
        this.contratoH = contratoH;
    }
    
    

}
