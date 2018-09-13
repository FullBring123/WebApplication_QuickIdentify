/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Windows8.1
 */
@Entity
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdproduit", query = "SELECT p FROM Produit p WHERE p.idproduit = :idproduit")
    , @NamedQuery(name = "Produit.findByType", query = "SELECT p FROM Produit p WHERE p.type = :type")
    , @NamedQuery(name = "Produit.findByCode", query = "SELECT p FROM Produit p WHERE p.code = :code")
    , @NamedQuery(name = "Produit.findByEtat", query = "SELECT p FROM Produit p WHERE p.etat = :etat")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduit")
    private Integer idproduit;
    @Size(max = 254)
    @Column(name = "type")
    private String type;
    @Size(max = 254)
    @Column(name = "code")
    private String code;
    @Size(max = 254)
    @Column(name = "etat")
    private String etat;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idutilisateur = new Utilisateur();

    public Produit() {
    }

    public Produit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public Integer getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Utilisateur getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduit != null ? idproduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idproduit == null && other.idproduit != null) || (this.idproduit != null && !this.idproduit.equals(other.idproduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produit[ idproduit=" + idproduit + " ]";
    }
    
}
