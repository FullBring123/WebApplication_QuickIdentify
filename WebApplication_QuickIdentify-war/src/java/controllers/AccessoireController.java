/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Produit;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.ProduitFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class AccessoireController {

    @EJB
    private ProduitFacadeLocal accessoireFacade;
    private Produit accessoire = new Produit();
    private List<Produit> mesAccessoires = new ArrayList<>();
    private List<Produit> produits = new ArrayList<>();
    private String operation;
    Utilisateur account = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

    /**
     * Creates a new instance of AccessoireController
     */
    public AccessoireController() {
    }

    @PostConstruct
    public void init() {
        mesAccessoires.clear();
        produits.clear();
        mesAccessoires.addAll(account.getProduitCollection());
        produits.addAll(accessoireFacade.findAll());
    }

    public void searchAccessoire(int id) {
        accessoire = accessoireFacade.find(id);
    }

    public void action(ActionEvent e) {
        CommandButton button = (CommandButton) e.getSource();
        operation = button.getWidgetVar();
    }

    
    public void linkProducts() {
        try {
            if (accessoireFacade.findByCode(accessoire.getCode()).isEmpty()) {
                accessoire.setIdproduit(accessoireFacade.nextId());
                accessoire.setIdutilisateur(account);
                accessoire.setCode(accessoire.getCode());
                accessoire.setType(accessoire.getType());
                accessoireFacade.edit(accessoire);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liaison etablie", "Ce produit est desormais lie a votre compte"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention!", "Ce produit est deja lie a un autre compte!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur lors de la liaison!", "Nous n'avons pu lier votre compte a ce produit"));
        } finally {
            init();
        }
    }

    public ProduitFacadeLocal getAccessoireFacade() {
        return accessoireFacade;
    }

    public void setAccessoireFacade(ProduitFacadeLocal accessoireFacade) {
        this.accessoireFacade = accessoireFacade;
    }

    public Produit getAccessoire() {
        return accessoire;
    }

    public void setAccessoire(Produit accessoire) {
        this.accessoire = accessoire;
    }

    public List<Produit> getMesAccessoires() {
        return mesAccessoires;
    }

    public void setMesAccessoires(List<Produit> mesAccessoires) {
        this.mesAccessoires = mesAccessoires;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Utilisateur getAccount() {
        return account;
    }

    public void setAccount(Utilisateur account) {
        this.account = account;
    }

}
