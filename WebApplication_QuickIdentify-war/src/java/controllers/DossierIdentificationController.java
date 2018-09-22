/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Dossieridentification;
import entities.Utilisateur;
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
import org.primefaces.model.UploadedFile;
import sessions.DossieridentificationFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class DossierIdentificationController implements Serializable {

    private String operation;
    private UploadedFile file;

    @EJB
    private DossieridentificationFacadeLocal dossierIdentificationFacade;
    private Dossieridentification dossierIdentification = new Dossieridentification();
    private Utilisateur compte = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
    private List<Utilisateur> users = new ArrayList<>();

    @EJB
    private UtilisateurFacadeLocal user;

    /**
     * Creates a new instance of DossierIdentification
     */
    public DossierIdentificationController() {

    }

    @PostConstruct
    public void init() {
        dossierIdentification = dossierIdentificationFacade.findByidutilisateur(compte);
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
    }

    public void prepareCreate(ActionEvent e) {
        if (dossierIdentification != null) {
            action(e);
        } else {
            dossierIdentification = new Dossieridentification();
            action(e);
        }

    }

    /**
     * Cette fonction permet au consommateur de modifier ses informations
     * personnelles
     */
    public void modifyDatas() {
        try {
            compte = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
            dossierIdentification.setIdutilisateur(compte);
            dossierIdentification.setNom(compte.getNom());
            dossierIdentification.setPrenom(compte.getPrenom());
            dossierIdentification.setNumcni(0);
            dossierIdentification.setNumrecepisse(0);
            dossierIdentification.setLieunais(dossierIdentification.getLieunais());
            dossierIdentification.setGenre(dossierIdentification.getGenre());
            dossierIdentification.setNationalite(dossierIdentification.getNationalite());
            dossierIdentification.setNommere(dossierIdentification.getNommere());
            dossierIdentification.setNummere(dossierIdentification.getNummere());
            dossierIdentification.setNompere(dossierIdentification.getNompere());
            dossierIdentification.setNumpere(dossierIdentification.getNumpere());
            dossierIdentification.setSituationmatrimoniale(dossierIdentification.getSituationmatrimoniale());
            dossierIdentification.setTelephone(dossierIdentification.getTelephone());
//            String nom = file.getFileName();
//            InputStream inputStream = file.getInputstream();
//            String uploads = "C:\\Users\\PC\\Desktop\\Système de collecte d'informations QRCODE\\Photos";
//            Files.copy(inputStream, new File(uploads, nom).toPath());
//            dossierIdentification.setPhoto(nom);
            dossierIdentificationFacade.edit(dossierIdentification);
            RequestContext.getCurrentInstance().execute("PF('wv_data').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Les modifications sur vos informations ont ete enregistrees!"));
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_data').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Échec de l'opération", "Vos informations n'ont pas pu être enregistrees!"));
        } finally {
            init();
        }
    }

    public void persist() {
        switch (operation) {
            case "modify":
                modifyDatas();
                break;
        }
    }

    public Dossieridentification getDossierIdentification() {
        return dossierIdentification;
    }

    public void setDossierIdentification(Dossieridentification dossierIdentification) {
        this.dossierIdentification = dossierIdentification;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
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

    public DossieridentificationFacadeLocal getDossierIdentificationFacade() {
        return dossierIdentificationFacade;
    }

    public void setDossierIdentificationFacade(DossieridentificationFacadeLocal dossierIdentificationFacade) {
        this.dossierIdentificationFacade = dossierIdentificationFacade;
    }

    public UtilisateurFacadeLocal getUser() {
        return user;
    }

    public void setUser(UtilisateurFacadeLocal user) {
        this.user = user;
    }

    public Utilisateur getCompte() {
        return compte;
    }

    public void setCompte(Utilisateur compte) {
        this.compte = compte;
    }

}
