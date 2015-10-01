/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Jerarquia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacBookPro
 */
@Local
public interface JerarquiaFacadeLocal {

    void create(Jerarquia jerarquia);

    void edit(Jerarquia jerarquia);

    void remove(Jerarquia jerarquia);

    Jerarquia find(Object id);

    List<Jerarquia> findAll();
    
    Jerarquia findByNombre(String nombre);
    
    List<Jerarquia> findByNombrelist(String nombre);

    List<Jerarquia> findRange(int[] range);

    int count();
    
}
