/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Produit;
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
import sessions.ProduitFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class ProduitController implements Serializable {

    @EJB
    private ProduitFacadeLocal produitFacade;
    private List<Produit> produits = new ArrayList<>();
    private List<Produit> mesProduits = new ArrayList<>();
    private Produit produit = new Produit();
    private String msg;
    private String operation;

    /**
     * Creates a new instance of ProduitController
     */
    public ProduitController() {
    }

    @PostConstruct
    public void init() {
        produits.clear();
        produits.addAll(produitFacade.findAll());
    }

    public void init2() {
        mesProduits.clear();
        mesProduits.addAll(produitFacade.findByLinkedProduits());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getSource();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        produit = new Produit();
        msg = "";
        action(e);
    }

    public void searchIdProduit(int id) {
        produit = produitFacade.find(id);
    }

    public void createProd() {
        try {
            if (produitFacade.findByCode(produit.getCode()).isEmpty()) {
                produit.setIdproduit(produit.getIdproduit());
                produit.setIdutilisateur(produit.getIdutilisateur());
                produit.setEtat("Actif");
                produitFacade.create(produit);
                RequestContext.getCurrentInstance().execute("PF('wv_produit').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Opération effectuée!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_produit').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Attention!!! Ce code a déja été attribué à un produit!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_produit').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Lourde Erreur!", "Échec de l'opération!"));
        } finally {
            init();
        }
    }

    public void editProd() {
        try {
            if (produitFacade.findByCode(produit.getCode()).isEmpty()) {
                produit.setIdproduit(produit.getIdproduit());
                produit.setIdutilisateur(produit.getIdutilisateur());
                produitFacade.edit(produit);
                RequestContext.getCurrentInstance().execute("PF('wv_produit1').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Opération effectuée!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('wv_produit1').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Attention!!! Ce code a déja été attribué à un produit!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_produit1').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Lourde Erreur!", "Échec de l'opération!"));
        } finally {
            init();
        }
    }

    public void removeProd() {
        try {
            produitFacade.remove(produit);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Suppression effectuée avec succès"));
            RequestContext.getCurrentInstance().execute("PF('wv_produit2').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Lourde Erreur!", "Échec de l'opération!"));
        } finally {
            init();
        }
    }

    public void linkProducts() {
        try {
            if (produitFacade.findByCode(produit.getCode()).isEmpty()) {
                produit.setIdproduit(produitFacade.nextId());
                produit.setIdutilisateur(produit.getIdutilisateur());
                produit.setCode(produit.getCode());
                produitFacade.edit(produit);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liaison etablie", "Ce produit est desormais lie a votre compte"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention!", "Ce produit est deja lie a un autre compte!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur lors de la liaison!", "Nous n'avons pu lier votre compte a ce produit"));
        } finally {
            init2();
        }
    }

    public ProduitFacadeLocal getProduitFacade() {
        return produitFacade;
    }

    public void setProduitFacade(ProduitFacadeLocal produitFacade) {
        this.produitFacade = produitFacade;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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

    public List<Produit> getMesProduits() {
        return mesProduits;
    }

    public void setMesProduits(List<Produit> mesProduits) {
        this.mesProduits = mesProduits;
    }

}
