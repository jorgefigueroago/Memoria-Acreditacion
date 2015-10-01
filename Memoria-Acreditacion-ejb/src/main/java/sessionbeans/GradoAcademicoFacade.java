/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.GradoAcademico;
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
public class GradoAcademicoFacade extends AbstractFacade<GradoAcademico> implements GradoAcademicoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public GradoAcademicoFacade() {
        super(GradoAcademico.class);
    }
    
    @Override
    public GradoAcademico findByNombre(String nombre){
        Query query;
        query = em.createNamedQuery("GradoAcademico.findByNombre")
                .setParameter("nombre", nombre);
        return (GradoAcademico) query.getSingleResult();
    }
    
    @Override
    public List<GradoAcademico> findByNombrelist(String nombre){
        Query query;
        query = em.createNamedQuery("GradoAcademico.findByNombre")
                .setParameter("nombre", nombre);
        return query.getResultList();
    }
   
   
    
}
