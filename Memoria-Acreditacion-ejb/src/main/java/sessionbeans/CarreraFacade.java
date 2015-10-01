/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Carrera;
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
public class CarreraFacade extends AbstractFacade<Carrera> implements CarreraFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }
    
    @Override
    public Carrera findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("Carrera.findByNombre")
                .setParameter("nombre", nombre);
        return (Carrera) query.getSingleResult();
    }
    @Override
    public List<Carrera> findByNombrelist(String nombre){
        Query query;
        query = em.createNamedQuery("Carrera.findByNombre")
                .setParameter("nombre", nombre);
        return query.getResultList();
    }
    
}
