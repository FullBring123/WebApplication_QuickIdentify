/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Agentcallcenter;
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
public class AgentcallcenterFacade extends AbstractFacade<Agentcallcenter> implements AgentcallcenterFacadeLocal {

    @PersistenceContext(unitName = "WebApplication_QuickIdentify-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgentcallcenterFacade() {
        super(Agentcallcenter.class);
    }
    
    @Override
    public List<Agentcallcenter> findByLogin(String login) {
        try {
            Query q = em.createNamedQuery("Agentcallcenter.findByLogin");
            q.setParameter("login", login);
            return (List<Agentcallcenter>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Agentcallcenter findByLoginPass(String login, String password) {
        try {
            Query q = em.createNamedQuery("Agentcallcenter.findByLoginPassword");
            q.setParameter("login", login).setParameter("password", password);
            return (Agentcallcenter) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Integer nextId() {
        try {
            Query q = em.createNamedQuery("Agentcallcenter.nextId");
            return ((Integer) q.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    
    @Override
    public void activateAgent(int id) {
        try {
            Query q = em.createQuery("UPDATE Agentcallcenter a SET a.etat = 'Actif' WHERE a.id = :id");
            q.setParameter("id", id);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deactivateAgent(int id) {
        try {
            Query q = em.createQuery("UPDATE Agentcallcenter a SET a.etat = 'Inactif' WHERE a.id = :id");
            q.setParameter("id", id);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
