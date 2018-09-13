/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Agentcallcenter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Windows8.1
 */
@Local
public interface AgentcallcenterFacadeLocal {

    void create(Agentcallcenter agentcallcenter);

    void edit(Agentcallcenter agentcallcenter);

    void remove(Agentcallcenter agentcallcenter);

    Agentcallcenter find(Object id);

    List<Agentcallcenter> findAll();

    List<Agentcallcenter> findRange(int[] range);

    int count();
    
    public List<Agentcallcenter> findByLogin(String login);
    
    public Agentcallcenter findByLoginPass(String login, String password);
    
    public Integer nextId();
    
    void activateAgent(int id);
    
    void deactivateAgent(int id);
    
}
