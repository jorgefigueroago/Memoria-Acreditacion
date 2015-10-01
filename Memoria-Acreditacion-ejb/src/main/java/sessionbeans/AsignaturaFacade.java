/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
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
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }
    
    @Override
    public Asignatura findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("Asignatura.findByNombre")
                .setParameter("nombre", nombre);
        return (Asignatura) query.getSingleResult();
    }
    
    @Override
    public List<Asignatura> findByNombrelist(String nombre){
        Query query;
        query = em.createNamedQuery("Asignatura.findByNombre")
                .setParameter("nombre", nombre);
        return query.getResultList();
    }
    
}
