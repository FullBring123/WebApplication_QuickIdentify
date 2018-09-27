/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Produit;
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
public class ProduitFacade extends AbstractFacade<Produit> implements ProduitFacadeLocal {

    @PersistenceContext(unitName = "WebApplication_QuickIdentify-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }

    @Override
    public List<Produit> findByCode(String code) {
        try {
            Query q = em.createNamedQuery("Produit.findByCode");
            q.setParameter("code", code);
            return (List<Produit>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer nextId() {
        try {
            Query q = em.createNamedQuery("Produit.nextId");
            return (Integer) q.getSingleResult() + 1;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Produit> findByLinkedProduits() {
        try {
            Query q = em.createQuery("SELECT p FROM Produit p GROUP BY p.idutilisateur");
            return (List<Produit>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Produit> findByIdProduct(int id) {
        try {
            Query q = em.createNamedQuery("Produit.findByProducts");
            q.setParameter("idproduit", id);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void activateProduit(int id) {
        try {
            Query q = em.createQuery("UPDATE Produit p SET p.etat = 'Actif' WHERE p.idproduit = :id");
            q.setParameter("id", id);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deactivateProduit(int id) {
        try {
            Query q = em.createQuery("UPDATE Produit p SET p.etat = 'Inactif' WHERE p.idproduit = :id");
            q.setParameter("id", id);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produit> findByNullUtilisateur() {
        try {
            Query query = em.createNamedQuery("Produit.findByNullUtilisateur");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Produit> findByIdUser(int id) {
        try {
            Query query = em.createNamedQuery("Produit.findByIdUser");
            query.setParameter("idutilisateur", id);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Produit> findByStatus() {
        try {
            Query query = em.createNamedQuery("Produit.findByStatus");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
