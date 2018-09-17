/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Produit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Windows8.1
 */
@Local
public interface ProduitFacadeLocal {

    void create(Produit produit);

    void edit(Produit produit);

    void remove(Produit produit);

    Produit find(Object id);

    List<Produit> findAll();

    List<Produit> findRange(int[] range);

    int count();
    
    Integer nextId();
    
    public List<Produit> findByCode(String code);
    
    List<Produit> findByLinkedProduits();
    
}
