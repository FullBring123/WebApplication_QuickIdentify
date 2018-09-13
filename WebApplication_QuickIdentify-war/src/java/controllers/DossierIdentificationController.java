/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Dossieridentification;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
    
    @EJB
    private UtilisateurFacadeLocal user;
    private List<Utilisateur> users = new ArrayList<>();
    
    @EJB
    private DossieridentificationFacadeLocal dossieridentificationFacade;
    private Dossieridentification dossierIdentification;
    private String operation;
    private UploadedFile file;

    /**
     * Creates a new instance of DossierIdentification
     */
    public DossierIdentificationController() {
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
    }

    public void prepareCreate(ActionEvent e) {
        dossierIdentification = new Dossieridentification();
        action(e);
    }

    public void editData() {
        try {
            dossierIdentification.setIdutilisateur(dossierIdentification.getIdutilisateur());
            dossierIdentification.setNom(dossierIdentification.getIdutilisateur().getNom());
            dossierIdentification.setPrenom(dossierIdentification.getIdutilisateur().getPrenom());
            String nom = file.getFileName();
            InputStream inputStream = file.getInputstream();
            String uploads = "C:\\Users\\Windows8.1\\Desktop\\Travail\\STAGE\\Stage 2017-2018\\UBstage\\Travaille Futur ingenieur\\Système de collecte d'informations QRCODE\\Photos de profil";
            Files.copy(inputStream, new File(uploads,nom).toPath());
            dossierIdentification.setPhoto(nom);
            dossieridentificationFacade.edit(dossierIdentification);
            RequestContext.getCurrentInstance().execute("PF('wv_data').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération efectuée", "Vos données ont été modifiées!"));
        } catch (IOException e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_data').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Échec de l'opération!"));
        }

    }

    public DossieridentificationFacadeLocal getDossieridentificationFacade() {
        return dossieridentificationFacade;
    }

    public void setDossieridentificationFacade(DossieridentificationFacadeLocal dossieridentificationFacade) {
        this.dossieridentificationFacade = dossieridentificationFacade;
    }

    public Dossieridentification getDossierIdentification() {
        return dossierIdentification;
    }

    public void setDossierIdentification(Dossieridentification dossierIdentification) {
        this.dossierIdentification = dossierIdentification;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public UtilisateurFacadeLocal getUser() {
        return user;
    }

    public void setUser(UtilisateurFacadeLocal user) {
        this.user = user;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
