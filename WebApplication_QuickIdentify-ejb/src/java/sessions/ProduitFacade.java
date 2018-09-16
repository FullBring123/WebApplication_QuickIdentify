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
    public List<Produit> findByLinkedProduits() {
        try {
            Query q = em.createQuery("SELECT P FROM Produit P GROUP BY P.idutilisateur");
            return (List<Produit>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
