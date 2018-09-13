/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class SessionController {

    @EJB
    private UtilisateurFacadeLocal currentUserFacade;
    private Utilisateur currentUser = new Utilisateur();
    private List<Utilisateur> users = new ArrayList<>();
    private Utilisateur admin = new Utilisateur();
    private UploadedFile file;
    private String operation;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("currentUser")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("portailcaptif.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String connectUser() {
        try {
            currentUser = currentUserFacade.findByLoginPass(currentUser.getLogin(), currentUser.getPassword());
            if (currentUser != null) {
                if (currentUser.getEtat().equals("Actif")) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                    return "utilisateur.xhtml?faces-redirect=true";
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Désolé! Vous ne pouvez accéder à votre session pour le moment!"));
                    return "portailcaptif.xhtml?faces-redirect=true";
                }
            } else {
                currentUser = new Utilisateur();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Login ou Mot de passe incorrect!"));
                return "portailcaptif.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            currentUser = new Utilisateur();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Login ou Mot de passe incorrect!"));
            return "portailcaptif.xhtml?faces-redirect=true";
        }
    }

    public String disconnectUser() {
        try {
//            logfile("Déconnexion", "Système");
            //On part dans la sessionMap retirer l'user connecté
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
            //On ferme la session liée à l'user
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "portailcaptif.xhtml?faces-redirect=true";
        }
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utilisateur currentUser) {
        this.currentUser = currentUser;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public UtilisateurFacadeLocal getCurrentUserFacade() {
        return currentUserFacade;
    }

    public void setCurrentUserFacade(UtilisateurFacadeLocal currentUserFacade) {
        this.currentUserFacade = currentUserFacade;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
