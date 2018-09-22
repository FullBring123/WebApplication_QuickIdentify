/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Dossieridentification;
import entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Windows8.1
 */
@Stateless
public class DossieridentificationFacade extends AbstractFacade<Dossieridentification> implements DossieridentificationFacadeLocal {

    @PersistenceContext(unitName = "WebApplication_QuickIdentify-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DossieridentificationFacade() {
        super(Dossieridentification.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query q = em.createNamedQuery("Dossieridentification.nextId");
            return (Integer) q.getSingleResult() + 1;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Dossieridentification findByidutilisateur(Utilisateur user) {
        try {
            Query q = em.createNamedQuery("Dossieridentification.findByidutilisateur");
            q.setParameter("idutilisateur", user);
            return (Dossieridentification) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
