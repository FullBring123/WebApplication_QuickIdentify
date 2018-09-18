/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Agentcallcenter;
import entities.Menu;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessions.AgentcallcenterFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class SessionController2 implements Serializable {

    @EJB
    private AgentcallcenterFacadeLocal agentcallcenterFacade;
    private Agentcallcenter sessionUser = new Agentcallcenter();
    private Boolean compte = false;
    private Boolean profil = false;
    private Boolean parame = false;
    private Boolean produi = false;
    private Boolean listep = false;
    private Boolean plus = false;
    private Boolean admini = false;
    private Boolean utilis = false;
    private Boolean perscc = false;
    private Boolean operat = false;
    private Boolean privil = false;
    private Boolean histor = false;
    private Boolean calcen = false;
    private Boolean enable = false;
    private Boolean disabl = false;
    private Boolean add = false;
    private Boolean delete = false;
    private Boolean displa = false;
    private Boolean modify = false;

    /**
     * Creates a new instance of SessionController2
     */
    public SessionController2() {
    }

    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("sessionUser")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("portailcaptif2.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String connect() {
        try {
            sessionUser = agentcallcenterFacade.findByLoginPass(sessionUser.getLogin(), sessionUser.getPassword());
            if (sessionUser != null) {
                if (sessionUser.getEtat().equals("Actif")) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionUser", sessionUser);
                    compte = sessionUser.getMenuCollection().contains(new Menu("compte"));
                    parame = sessionUser.getMenuCollection().contains(new Menu("parame"));
                    profil = sessionUser.getMenuCollection().contains(new Menu("profil"));
                    produi = sessionUser.getMenuCollection().contains(new Menu("produi"));
                    listep = sessionUser.getMenuCollection().contains(new Menu("listep"));
                    plus = sessionUser.getMenuCollection().contains(new Menu("plus"));
                    admini = sessionUser.getMenuCollection().contains(new Menu("admini"));
                    utilis = sessionUser.getMenuCollection().contains(new Menu("utilis"));
                    perscc = sessionUser.getMenuCollection().contains(new Menu("perscc"));
                    operat = sessionUser.getMenuCollection().contains(new Menu("operat"));
                    privil = sessionUser.getMenuCollection().contains(new Menu("privil"));
                    histor = sessionUser.getMenuCollection().contains(new Menu("histor"));
                    calcen = sessionUser.getMenuCollection().contains(new Menu("calcen"));
                    enable = sessionUser.getMenuCollection().contains(new Menu("enable"));
                    disabl = sessionUser.getMenuCollection().contains(new Menu("disabl"));
                    add = sessionUser.getMenuCollection().contains(new Menu("add"));
                    delete = sessionUser.getMenuCollection().contains(new Menu("delete"));
                    displa = sessionUser.getMenuCollection().contains(new Menu("displa"));
                    modify = sessionUser.getMenuCollection().contains(new Menu("modify"));

                    return "index.xhtml?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Désolé", "Vous ne pouvez plus vous connecter à ce compte"));
                    return "portailcaptif2.xhtml?faces-redirect=true";
                }
            } else {
                sessionUser = new Agentcallcenter();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez vous rapprocher de l'administrateur afin de vous ajouter comme nouvel utilisateur!"));
                return "portailcaptif2.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionUser = new Agentcallcenter();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Échec de l'opération!"));
            return "portailcaptif2.xhtml?faces-redirect=true";
        }
    }

    public String disconnect() {
        try {
//            logfile("Déconnexion", "Système");
            //On part dans la sessionMap retirer l'user connecté
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sessionUser");
            //On ferme la session liée à l'user
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "portailcaptif2.xhtml?faces-redirect=true";
        }
    }

    public AgentcallcenterFacadeLocal getAgentcallcenterFacade() {
        return agentcallcenterFacade;
    }

    public void setAgentcallcenterFacade(AgentcallcenterFacadeLocal agentcallcenterFacade) {
        this.agentcallcenterFacade = agentcallcenterFacade;
    }

    public Agentcallcenter getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(Agentcallcenter sessionUser) {
        this.sessionUser = sessionUser;
    }

    public Boolean getCompte() {
        return compte;
    }

    public Boolean getProfil() {
        return profil;
    }

    public void setProfil(Boolean profil) {
        this.profil = profil;
    }

    public void setCompte(Boolean compte) {
        this.compte = compte;
    }

    public Boolean getParame() {
        return parame;
    }

    public void setParame(Boolean parame) {
        this.parame = parame;
    }

    public Boolean getProdui() {
        return produi;
    }

    public void setProdui(Boolean produi) {
        this.produi = produi;
    }

    public Boolean getListep() {
        return listep;
    }

    public void setListep(Boolean listep) {
        this.listep = listep;
    }

    public Boolean getPlus() {
        return plus;
    }

    public void setPlus(Boolean plus) {
        this.plus = plus;
    }

    public Boolean getAdmini() {
        return admini;
    }

    public void setAdmini(Boolean admini) {
        this.admini = admini;
    }

    public Boolean getUtilis() {
        return utilis;
    }

    public void setUtilis(Boolean utilis) {
        this.utilis = utilis;
    }

    public Boolean getPerscc() {
        return perscc;
    }

    public void setPerscc(Boolean perscc) {
        this.perscc = perscc;
    }

    public Boolean getOperat() {
        return operat;
    }

    public void setOperat(Boolean operat) {
        this.operat = operat;
    }

    public Boolean getPrivil() {
        return privil;
    }

    public void setPrivil(Boolean privil) {
        this.privil = privil;
    }

    public Boolean getHistor() {
        return histor;
    }

    public void setHistor(Boolean histor) {
        this.histor = histor;
    }

    public Boolean getCalcen() {
        return calcen;
    }

    public void setCalcen(Boolean calcen) {
        this.calcen = calcen;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getDisabl() {
        return disabl;
    }

    public void setDisabl(Boolean disabl) {
        this.disabl = disabl;
    }

    public Boolean getDispla() {
        return displa;
    }

    public void setDispla(Boolean displa) {
        this.displa = displa;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getModify() {
        return modify;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public Boolean getAdd() {
        return add;
    }

    public void setAdd(Boolean add) {
        this.add = add;
    }

}
