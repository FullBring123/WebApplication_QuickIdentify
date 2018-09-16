/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Dossieridentification;
import entities.Utilisateur;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.model.UploadedFile;
import sessions.DossieridentificationFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author PC
 */
public class SessionsController implements Serializable {

    @EJB
    private DossieridentificationFacadeLocal dossieridentificationFacade;
    private Dossieridentification dossieridentification;
    private Dossieridentification dossier;
//    
//    @EJB
//    private ProduitFacadeLocal produitFacade;
//    private Produit produit = new Produit();
//    private List<Produit> mesProduits = new ArrayList<>();

    @EJB
    private UtilisateurFacadeLocal currentUserFacade;
    private Utilisateur currentUser = new Utilisateur();
    private Utilisateur admin = new Utilisateur();
    private List<Utilisateur> users = new ArrayList<>();
    private UploadedFile file;
    private String operation;

    /**
     * Creates a new instance of SessionsController
     */
    public SessionsController() {
    }

//    @PostConstruct
//    private void init() {
//        mesProduits.clear();
//        mesProduits.addAll(produitFacade.findByLinkedProduits());
//    }
    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("currentUser")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("portailcaptif.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
    }

    public void prepareCreate(ActionEvent e) {
        dossieridentification = new Dossieridentification();
        action(e);
    }

    public String connectUser() {
        try {
            currentUser = currentUserFacade.findByLoginPass(currentUser.getLogin(), currentUser.getPassword());
            if (currentUser != null) {
                if (currentUser.getEtat().equals("Actif")) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                    return "utilisateur.xhtml?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Échec de connexion!", "Desole, vous ne pouvez acceder a votre session pour le moment!"));
                    return "portailcaptif.xhtml?faces-redirect=true";
                }
            } else {
                currentUser = new Utilisateur();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur!", "Login ou mot de passe incorrect!"));
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

    /**
     * Cette fonction permet au consommateur de modifier ses informations
     * personnelles
     */
    public void modifyDatas() {
        try {
            dossieridentification.setIddossier(dossieridentificationFacade.nextId());
            dossieridentification.setIdutilisateur(getCurrentUser());
            dossieridentification.setNumcni(0);
            dossieridentification.setNumrecepisse(10);
            String nom = file.getFileName();
            InputStream inputStream = file.getInputstream();
            String uploads = "C:\\Users\\PC\\Desktop\\Système de collecte d'informations QRCODE\\Photos";
            Files.copy(inputStream, new File(uploads, nom).toPath());
            dossieridentification.setPhoto(nom);
            dossieridentificationFacade.edit(dossieridentification);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Les modifications sur vos informations ont ete enregistrees!"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Échec de l'opération", "Vos informations n'ont pas pu être editees!"));
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

    public UtilisateurFacadeLocal getCurrentUserFacade() {
        return currentUserFacade;
    }

    public void setCurrentUserFacade(UtilisateurFacadeLocal currentUserFacade) {
        this.currentUserFacade = currentUserFacade;
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utilisateur currentUser) {
        this.currentUser = currentUser;
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

    public DossieridentificationFacadeLocal getDossieridentificationFacade() {
        return dossieridentificationFacade;
    }

    public void setDossieridentificationFacade(DossieridentificationFacadeLocal dossieridentificationFacade) {
        this.dossieridentificationFacade = dossieridentificationFacade;
    }

    public Dossieridentification getDossieridentification() {
        return dossieridentification;
    }

    public void setDossieridentification(Dossieridentification dossieridentification) {
        this.dossieridentification = dossieridentification;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Dossieridentification getDossier() {
        return dossier;
    }

    public void setDossier(Dossieridentification dossier) {
        this.dossier = dossier;
    }

//    public ProduitFacadeLocal getProduitFacade() {
//        return produitFacade;
//    }
//
//    public void setProduitFacade(ProduitFacadeLocal produitFacade) {
//        this.produitFacade = produitFacade;
//    }
//
//    public Produit getProduit() {
//        return produit;
//    }
//
//    public void setProduit(Produit produit) {
//        this.produit = produit;
//    }
//
//    public List<Produit> getMesProduits() {
//        return mesProduits;
//    }
//
//    public void setMesProduits(List<Produit> mesProduits) {
//        this.mesProduits = mesProduits;
//    }
}
