/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Jerarquia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class JerarquiaFacade extends AbstractFacade<Jerarquia> implements JerarquiaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Jerarquia findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("Jerarquia.findByNombre")
                .setParameter("nombre", nombre);
        return (Jerarquia) query.getSingleResult();
    }
    @Override
    public List<Jerarquia> findByNombrelist(String nombre){
        Query query;
        query = em.createNamedQuery("Jerarquia.findByNombre")
                .setParameter("nombre", nombre);
        return query.getResultList();
    }
    

    public JerarquiaFacade() {
        super(Jerarquia.class);
    }
    
}
