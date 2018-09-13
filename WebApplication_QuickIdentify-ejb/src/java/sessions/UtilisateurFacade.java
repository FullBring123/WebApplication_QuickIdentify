/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Windows8.1
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "WebApplication_QuickIdentify-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    @Override
    public List<Utilisateur> findByLogin(String login) {
        try {
            Query q = em.createNamedQuery("Utilisateur.findByLogin");
            q.setParameter("login", login);
            return (List<Utilisateur>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Integer nextId() {
        try {
            Query q = em.createNamedQuery("Utilisateur.nextId");
            return (Integer) q.getSingleResult() + 1;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Utilisateur findByLoginPass(String login, String pass) {
        try {
            Query q = em.createNamedQuery("Utilisateur.findByLoginPassword");
            q.setParameter("login", login).setParameter("password", pass);
            return (Utilisateur) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public void activateUser(int id) {
        try {
            Query q = em.createQuery("UPDATE Utilisateur u SET u.etat = 'Actif' WHERE u.idutilisateur = :id");
            q.setParameter("id", id);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deactivateUser(int id) {
        try {
            Query q = em.createQuery("UPDATE Utilisateur u SET u.etat = 'Inactif' WHERE u.idutilisateur = :id");
            q.setParameter("id", id);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
