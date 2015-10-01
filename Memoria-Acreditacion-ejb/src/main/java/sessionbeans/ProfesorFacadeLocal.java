/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.GradoAcademico;
import entities.Jerarquia;
import entities.Profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacBookPro
 */
@Local
public interface ProfesorFacadeLocal {

    void create(Profesor profesor);

    void edit(Profesor profesor);

    void remove(Profesor profesor);
   

    Profesor find(Object id);

    List<Profesor> findAll();
    
    List<Profesor> findByJerarquia(Jerarquia j);
    
    List<Profesor> findByGrado(GradoAcademico g);
    
    public List<Profesor> findByJerarYGrado(Jerarquia j, GradoAcademico g);
    
    public List<Profesor> findByJerarYContrato(Jerarquia j, String c);
    
    public List<Profesor> findByAno(int ano);
    
    public List<Profesor> findByAnoRetiro(int ano);
            
    

    List<Profesor> findRange(int[] range);

    int count();
    
}
