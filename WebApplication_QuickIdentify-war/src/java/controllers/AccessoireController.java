/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Produit;
import entities.Utilisateur;
import java.io.Serializable;
import java.sql.*;
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
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author Windows8.1
 */
public class AccessoireController implements Serializable/*, Converter*/ {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    Utilisateur account = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

    @EJB
    private ProduitFacadeLocal produitFacade;
    private Produit produit = new Produit();
    private List<Produit> produits = new ArrayList<>();
    private List<Produit> lesAccessoires = new ArrayList<>();
    Timestamp date = new Timestamp(System.currentTimeMillis());
    private String operation;

    /**
     * Creates a new instance of AccessoireController
     */
    public AccessoireController() {
    }

    @PostConstruct
    public void init() {
        lesAccessoires.clear();
        lesAccessoires.addAll(produitFacade.findByIdUser(account.getIdutilisateur()));
        produits.clear();
        produits.addAll(produitFacade.findByStatus());
    }

    public void initBox() {
        produits.clear();
        produits.addAll(produitFacade.findByStatus());
    }

    public List<Produit> getProducts() {
        return produits;
    }

    public void searchAccessoire(int id) {
        produit = produitFacade.find(id);
    }

    public void action(ActionEvent e) {
        CommandButton button = (CommandButton) e.getSource();
        operation = button.getWidgetVar();
    }

    public void prepareLink(ActionEvent e) {
        if (produit != null) {
            action(e);
            initBox();
        }
    }

    /**
     * Cette methode permet de ne lister que chez les utilisateurs, les produits
     * qu'ils ont lies a leur propre compte
     */
    public void linkProducts() {
        try {
            produit = produitFacade.find(produit.getIdproduit());
//            if (produitFacade.findByIdProduct(produit.getIdproduit()).isEmpty()) {
//                RequestContext.getCurrentInstance().execute("PF('wv_link').hide()");
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention!", "Ce produit est deja lie a un autre compte!"));
//            } else {
            account = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
            produit.setIdutilisateur(account);
            produit.setStatus(2);
            produitFacade.edit(produit);
            RequestContext.getCurrentInstance().execute("PF('wv_link').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liaison etablie " + produit.getIdproduit(), "Ce produit est desormais lie a votre compte"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('wv_link').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur lors de la liaison!", "Nous n'avons pu lier votre compte a ce produit"));
        } finally {
            init();
        }
    }

    public String setColor(String state) {
        return (state.equals("Actif") ? "#00cc66" : "#ff4d4d");
    }

    public void activate() {
        try {
            if (produit.getEtat().equals("Inactif")) {
                produitFacade.activateProduit(produit.getIdproduit());
                produit.setStatus(2);
                produitFacade.edit(produit);
                RequestContext.getCurrentInstance().execute("PF('enable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation effectuée!", "Le produit a été activé!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('enable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Activation impossible!", "Cet produit a déjà été activé!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void deactivate() {
        try {
            if (produit.getEtat().equals("Actif")) {
                produitFacade.deactivateProduit(produit.getIdproduit());
                produit.setStatus(1);
                produitFacade.edit(produit);
                RequestContext.getCurrentInstance().execute("PF('disable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Desactivation effectuée!", "Le produit a été désactivé!"));
            } else {
                RequestContext.getCurrentInstance().execute("PF('disable').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Désactivation impossible!", "Cet produit a déjà été désactivé!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void deleteAcc() {
        RequestContext.getCurrentInstance().execute("PF('delete').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Indisponible", "Aucune implementation!"));
    }

//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value != null && value.trim().length() > 0) {
//            ThemeService service = (ThemeService) context.getExternalContext().getApplicationMap().get("themeService");
//            return service.getThemes().get(Integer.parseInt(value));
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if(value != null) {
//            return String.valueOf(((Theme) value).getId());
//        }
//        else {
//            return null;
//        }
//    }
    public void persist() {
        switch (operation) {
            case "doLink":
                linkProducts();
                break;
        }
    }

    public ProduitFacadeLocal getProduitFacade() {
        return produitFacade;
    }

    public void setProduitFacade(ProduitFacadeLocal produitFacade) {
        this.produitFacade = produitFacade;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getLesAccessoires() {
        return lesAccessoires;
    }

    public void setLesAccessoires(List<Produit> lesAccessoires) {
        this.lesAccessoires = lesAccessoires;
    }

    public UtilisateurFacadeLocal getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(UtilisateurFacadeLocal utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Utilisateur getAccount() {
        return account;
    }

    public void setAccount(Utilisateur account) {
        this.account = account;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
