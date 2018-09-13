/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Agentcallcenter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.AgentcallcenterFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class UtilisateurCallCenterController implements Serializable {

    @EJB
    private AgentcallcenterFacadeLocal agentcallcenterFacade;
    private List<Agentcallcenter> agents = new ArrayList<>();
    private Agentcallcenter agent = new Agentcallcenter();
    private String operation;

    /**
     * Creates a new instance of UtilisateurCallCenterController
     */
    public UtilisateurCallCenterController() {
    }

    @PostConstruct
    public void init() {
        agents.clear();
        agents.addAll(agentcallcenterFacade.findAll());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
    }

    public void prepareCreate(ActionEvent e) {
        agent = new Agentcallcenter();
        action(e);
    }

    public void searchIdPersonnel(int id) {
        agent = agentcallcenterFacade.find(id);
    }

    public void activate() {
        try {
            if (agent.getEtat().equals("Inactif")) {
                agentcallcenterFacade.activateAgent(agent.getId());
                RequestContext.getCurrentInstance().execute("PF('wv_enable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée!", "Activation OK!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_enable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention!", "Compte deja active!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_enable').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur!", "Échec lors de l'activation!"));
        } finally {
            init();
        }
    }

    public void deactivate() {
        try {
            if (agent.getEtat().equals("Actif")) {
                agentcallcenterFacade.deactivateAgent(agent.getId());
                RequestContext.getCurrentInstance().execute("PF('wv_disable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée!", "Desactivation OK!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_disable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention!", "Compte deja desactive!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_disable').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur!", "Une erreur s'est produite durant la désactivation!"));

        } finally {
            init();
        }
    }

    public void createCC() {
        try {
            if (agentcallcenterFacade.findByLogin(agent.getLogin()).isEmpty()) {
                agent.setId(agentcallcenterFacade.nextId());
                agent.setPassword(agent.getPassword());
                agent.setEtat("Actif");
                agentcallcenterFacade.create(agent);
                RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Opération exécutée", "Un nouvel utilisateur a été ajouté!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login existant", "Désolé, Ce login a déja été attribué!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_personnel').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Échec de l'opération", "Bien vouloir revoir vos changements"));
        } finally {
            init();
        }
    }

    public void editCC() {
        try {
            if (agentcallcenterFacade.findByLogin(agent.getLogin()).isEmpty()) {
                agent.setId(agent.getId());
                agent.setPassword(agent.getPassword());
                agentcallcenterFacade.edit(agent);
                RequestContext.getCurrentInstance().execute("PF('wv_personnel1').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Modification effectuée", ""));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_personnel1').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login existant", "Désolé, Ce login a déja été attribué!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_personnel1').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Échec de l'opération", "Bien vouloir revoir vos changements"));
        } finally {
            init();
        }
    }

    public void removeCC() {
        try {
            agentcallcenterFacade.remove(agent);
            RequestContext.getCurrentInstance().execute("PF('wv_personnel2').hide()");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Suppression effectuée avec succès"));
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_personnel2').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Échec de l'opération", "Bien vouloir revoir vos changements"));
        } finally {
            init();
        }
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

    public Agentcallcenter getAgent() {
        return agent;
    }

    public void setAgent(Agentcallcenter agent) {
        this.agent = agent;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}
