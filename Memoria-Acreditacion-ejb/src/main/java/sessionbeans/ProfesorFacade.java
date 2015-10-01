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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class ProfesorFacade extends AbstractFacade<Profesor> implements ProfesorFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        super(Profesor.class);
    }
    
    
    @Override
    public List<Profesor> findByJerarquia(Jerarquia j){
        Query query;
        query = em.createNamedQuery("Profesor.findByJerarquia")
                .setParameter("jerarquia", j);
        return query.getResultList();
    }
    
    @Override
    public List<Profesor> findByGrado(GradoAcademico g){
        Query query;
        query = em.createNamedQuery("Profesor.findByGrado")
                .setParameter("grado", g);
        return query.getResultList();
    }
   
    
    @Override
    public List<Profesor> findByJerarYGrado(Jerarquia j, GradoAcademico g){
        Query query;
        query = em.createNamedQuery("Profesor.findByJerarYGrado")
                .setParameter("jerarquia", j)
                .setParameter("grado", g);
        return query.getResultList();
    }
    
    @Override
    public List<Profesor> findByJerarYContrato(Jerarquia j, String c){
        Query query;
        query = em.createNamedQuery("Profesor.findByJerarYContrato")
                .setParameter("jerarquia", j)
                .setParameter("contrato", c);
        return query.getResultList();
    }
    @Override
    public List<Profesor> findByAno(int ano){
        Query query;
        query = em.createNamedQuery("Profesor.findByAno")
                .setParameter("ano", ano)
                .setParameter("vigente", true);
        return query.getResultList();
    }
    
    @Override
    public List<Profesor> findByAnoRetiro(int ano){
        Query query;
        query = em.createNamedQuery("Profesor.findByAnoRetiro")
                .setParameter("ano", ano)
                .setParameter("vigente", false);
        return query.getResultList();
    }
    
    
}
