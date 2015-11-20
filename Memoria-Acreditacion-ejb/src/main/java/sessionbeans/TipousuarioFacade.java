/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Tipousuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class TipousuarioFacade extends AbstractFacade<Tipousuario> implements TipousuarioFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_Memoria-Acreditacion-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipousuarioFacade() {
        super(Tipousuario.class);
    }
    
    @Override
    public Tipousuario findByName(String tipo){
        Query query;
        query = em.createNamedQuery("Tipousuario.findByNombreTipo")
                .setParameter("nombreTipo", tipo);
        try{
            return (Tipousuario) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    
    }
    
}
