/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    private String operation;

    /**
     * Creates a new instance of AccessoireController
     */
    public AccessoireController() {
    }
    
    @PostConstruct
    public void init() {
        mesAccessoires.clear();
        mesAccessoires.addAll(accessoireFacade.findAll());
    }
    
    public void searchAccessoire(int id) {
        accessoire = accessoireFacade.find(id);
    }
    
    public void action(ActionEvent e) {
        CommandButton button = (CommandButton) e.getSource();
        operation = button.getWidgetVar();
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
    
}
