/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Agentcallcenter;
import entities.Menu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.model.DualListModel;
import sessions.AgentcallcenterFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.OperationFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class PrivilegesController implements Serializable, Converter {
    
    @EJB
    private OperationFacadeLocal operationFacade;
    
    @EJB
    private AgentcallcenterFacadeLocal agentcallcenterFacade;
    private List<Agentcallcenter> agents = new ArrayList<>();
    private Agentcallcenter accesseur = new Agentcallcenter();
    private Integer idAccesseur;
    private String operation;
    
    @EJB
    private MenuFacadeLocal menuFacade;
    private DualListModel<Menu> privileges = new DualListModel<>(new ArrayList<>(), new ArrayList<>());

    /**
     * Creates a new instance of PrivilegesController
     */
    public PrivilegesController() {
    }
    
    @PostConstruct
    public void init() {
        chargerListMenu();
        chargerListAccesseurs();
    }
    
    public void chargerListMenu() {
        privileges.getSource().clear();
        privileges.getSource().addAll(menuFacade.findAll());
    }

    public void chargerListAccesseurs() {
        agents.clear();
        agents.addAll(agentcallcenterFacade.findAll());
    }
    
    public void selectAccesseur() {
        accesseur = agentcallcenterFacade.find(idAccesseur);
        chargerListMenu();
        privileges.getTarget().clear();
        //On charge dans la liste de destination les menus propres à l'utilisateur sélectionné(idUtilisateur)
        privileges.getTarget().addAll(accesseur.getMenuCollection());
        //On supprime les menus de la liste source qui sont déja dans la liste de destination
        privileges.getSource().removeAll(privileges.getTarget());
    }
    
    public void savePrivileges() {
        try {
//            logfile("Attribution des privilèges : ", utilisateur.getNom() + " " + utilisateur.getPrenom());
            accesseur.setMenuCollection(privileges.getTarget());
            agentcallcenterFacade.edit(accesseur);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Les privilèges ont été attribués!"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur", "Échec de l'opération!"));
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return menuFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Menu) value).getIdmenu().toString();
    }

    public OperationFacadeLocal getOperationFacade() {
        return operationFacade;
    }

    public void setOperationFacade(OperationFacadeLocal operationFacade) {
        this.operationFacade = operationFacade;
    }

    public AgentcallcenterFacadeLocal getAgentcallcenterFacade() {
        return agentcallcenterFacade;
    }

    public void setAgentcallcenterFacade(AgentcallcenterFacadeLocal agentcallcenterFacade) {
        this.agentcallcenterFacade = agentcallcenterFacade;
    }

    public List<Agentcallcenter> getAgents() {
        return agents;
    }

    public void setAgents(List<Agentcallcenter> agents) {
        this.agents = agents;
    }

    public Agentcallcenter getAccesseur() {
        return accesseur;
    }

    public void setAccesseur(Agentcallcenter accesseur) {
        this.accesseur = accesseur;
    }

    public Integer getIdAccesseur() {
        return idAccesseur;
    }

    public void setIdAccesseur(Integer idAccesseur) {
        this.idAccesseur = idAccesseur;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MenuFacadeLocal getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacadeLocal menuFacade) {
        this.menuFacade = menuFacade;
    }

    public DualListModel<Menu> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(DualListModel<Menu> privileges) {
        this.privileges = privileges;
    }
    
}
