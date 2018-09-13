/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Windows8.1
 */
@Local
public interface UtilisateurFacadeLocal {

    void create(Utilisateur utilisateur);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);

    Utilisateur find(Object id);

    List<Utilisateur> findAll();

    List<Utilisateur> findRange(int[] range);

    int count();
    
    public List<Utilisateur> findByLogin(String login);
    
    Integer nextId();
    
    Utilisateur findByLoginPass(String login, String pass);
    
    void activateUser(int id);
    
    void deactivateUser(int id);
    
}
