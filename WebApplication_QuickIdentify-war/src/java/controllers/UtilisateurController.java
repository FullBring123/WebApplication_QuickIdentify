/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Utilisateur;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class UtilisateurController implements Serializable {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private Utilisateur utilisateur = new Utilisateur();
    private UploadedFile file;
    private String msg;
    private String operation;

    /**
     * Creates a new instance of UtilisateurController
     */
    public UtilisateurController() {
    }

    @PostConstruct
    public void init() {
        utilisateurs.clear();
        utilisateurs.addAll(utilisateurFacade.findAll());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        utilisateur = new Utilisateur();
        msg = "";
        action(e);
    }

    public void searchIdUser(int id) {
        utilisateur = utilisateurFacade.find(id);
    }

    public void activateAccount() {
        try {
            if (utilisateur.getEtat().equals("Inactif")) {
                utilisateurFacade.activateUser(utilisateur.getIdutilisateur());
                RequestContext.getCurrentInstance().execute("PF('wv_enable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée!", "L'utilisateur a été activé!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_enable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Activation impossible!", "Cet utilisateur est déjà actif!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void deactivateAccount() {
        try {
            if (utilisateur.getEtat().equals("Actif")) {
                utilisateurFacade.deactivateUser(utilisateur.getIdutilisateur());
                RequestContext.getCurrentInstance().execute("PF('wv_disable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée!", "L'utilisateur a été désactivé!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_disable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Désactivation impossible!", "Cet utilisateur est déjà désactivé!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void createUser() {
        try {
            if (utilisateurFacade.findByLogin(utilisateur.getLogin()).isEmpty()) {
                utilisateur.setIdutilisateur(utilisateurFacade.nextId());
                utilisateur.setPassword(utilisateur.getPassword());
                utilisateur.setEtat("Actif");
                String nom = file.getFileName();
                InputStream inputStream = file.getInputstream();
                String uploads = "C:\\Users\\PC\\Desktop\\Système de collecte d'informations QRCODE\\Photos";
                Files.copy(inputStream, new File(uploads, nom).toPath());
                utilisateur.setPhoto(nom);
                utilisateurFacade.create(utilisateur);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Opération effectuée!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention!", "Utilisateur existant!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Échec de l'opération!", ""));
        }
    }

//    public void editUser() {
//        try {
//            if (utilisateurFacade.findByLogin(utilisateur.getLogin()).isEmpty()) {
//                utilisateur.setIdutilisateur(utilisateur.getIdutilisateur());
//                utilisateur.setPassword(((Integer) utilisateur.getPassword().hashCode()).toString());
//                utilisateurFacade.edit(utilisateur);
//                RequestContext.getCurrentInstance().execute("PF('wv_user2').hide()");
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Opération effectuée!"));
//            } else {
//                RequestContext.getCurrentInstance().execute("PF('wv_user2').hide()");
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Utilisateur existant!"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            RequestContext.getCurrentInstance().execute("PF('wv_user2').hide()");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Lourde Erreur!", "Échec de l'opération!"));
//        } finally {
//            init();
//        }
//    }

    public void removeUser() {
        try {
            utilisateurFacade.remove(utilisateur);
            RequestContext.getCurrentInstance().execute("PF('wv_delete').hide()");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Suppression effectuée avec succès"));
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_delete').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Lourde Erreur!", "Échec de l'opération!"));
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rien ne s'est passe!", ""));
            init();
        }
    }

    public UtilisateurFacadeLocal getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(UtilisateurFacadeLocal utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
