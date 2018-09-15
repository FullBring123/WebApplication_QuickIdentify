/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Windows8.1
 */
@Entity
@Table(name = "dossieridentification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dossieridentification.findAll", query = "SELECT d FROM Dossieridentification d")
    , @NamedQuery(name = "Dossieridentification.nextId", query = "SELECT MAX(d.iddossier) FROM Dossieridentification d")
    , @NamedQuery(name = "Dossieridentification.findByIddossier", query = "SELECT d FROM Dossieridentification d WHERE d.iddossier = :iddossier")
    , @NamedQuery(name = "Dossieridentification.findByNumcni", query = "SELECT d FROM Dossieridentification d WHERE d.numcni = :numcni")
    , @NamedQuery(name = "Dossieridentification.findByNumrecepisse", query = "SELECT d FROM Dossieridentification d WHERE d.numrecepisse = :numrecepisse")
    , @NamedQuery(name = "Dossieridentification.findByNom", query = "SELECT d FROM Dossieridentification d WHERE d.nom = :nom")
    , @NamedQuery(name = "Dossieridentification.findByPrenom", query = "SELECT d FROM Dossieridentification d WHERE d.prenom = :prenom")
    , @NamedQuery(name = "Dossieridentification.findByDatenais", query = "SELECT d FROM Dossieridentification d WHERE d.datenais = :datenais")
    , @NamedQuery(name = "Dossieridentification.findByLieunais", query = "SELECT d FROM Dossieridentification d WHERE d.lieunais = :lieunais")
    , @NamedQuery(name = "Dossieridentification.findByGenre", query = "SELECT d FROM Dossieridentification d WHERE d.genre = :genre")
    , @NamedQuery(name = "Dossieridentification.findByTelephone", query = "SELECT d FROM Dossieridentification d WHERE d.telephone = :telephone")
    , @NamedQuery(name = "Dossieridentification.findByNationalite", query = "SELECT d FROM Dossieridentification d WHERE d.nationalite = :nationalite")
    , @NamedQuery(name = "Dossieridentification.findBySituationmatrimoniale", query = "SELECT d FROM Dossieridentification d WHERE d.situationmatrimoniale = :situationmatrimoniale")
    , @NamedQuery(name = "Dossieridentification.findByNompere", query = "SELECT d FROM Dossieridentification d WHERE d.nompere = :nompere")
    , @NamedQuery(name = "Dossieridentification.findByNumpere", query = "SELECT d FROM Dossieridentification d WHERE d.numpere = :numpere")
    , @NamedQuery(name = "Dossieridentification.findByNommere", query = "SELECT d FROM Dossieridentification d WHERE d.nommere = :nommere")
    , @NamedQuery(name = "Dossieridentification.findByNummere", query = "SELECT d FROM Dossieridentification d WHERE d.nummere = :nummere")
    , @NamedQuery(name = "Dossieridentification.findByPhoto", query = "SELECT d FROM Dossieridentification d WHERE d.photo = :photo")})
public class Dossieridentification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddossier")
    private Integer iddossier;
    @Column(name = "numcni")
    private Integer numcni;
    @Column(name = "numrecepisse")
    private Integer numrecepisse;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "datenais")
    @Temporal(TemporalType.DATE)
    private Date datenais;
    @Size(max = 254)
    @Column(name = "lieunais")
    private String lieunais;
    @Size(max = 254)
    @Column(name = "genre")
    private String genre;
    @Column(name = "telephone")
    private BigInteger telephone;
    @Size(max = 254)
    @Column(name = "nationalite")
    private String nationalite;
    @Size(max = 254)
    @Column(name = "situationmatrimoniale")
    private String situationmatrimoniale;
    @Size(max = 254)
    @Column(name = "nompere")
    private String nompere;
    @Column(name = "numpere")
    private BigInteger numpere;
    @Size(max = 254)
    @Column(name = "nommere")
    private String nommere;
    @Column(name = "nummere")
    private BigInteger nummere;
    @Size(max = 254)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne
    private Utilisateur idutilisateur = new Utilisateur();

    public Dossieridentification() {
    }

    public Dossieridentification(Integer iddossier) {
        this.iddossier = iddossier;
    }

    public Integer getIddossier() {
        return iddossier;
    }

    public void setIddossier(Integer iddossier) {
        this.iddossier = iddossier;
    }

    public Integer getNumcni() {
        return numcni;
    }

    public void setNumcni(Integer numcni) {
        this.numcni = numcni;
    }

    public Integer getNumrecepisse() {
        return numrecepisse;
    }

    public void setNumrecepisse(Integer numrecepisse) {
        this.numrecepisse = numrecepisse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenais() {
        return datenais;
    }

    public void setDatenais(Date datenais) {
        this.datenais = datenais;
    }

    public String getLieunais() {
        return lieunais;
    }

    public void setLieunais(String lieunais) {
        this.lieunais = lieunais;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigInteger getTelephone() {
        return telephone;
    }

    public void setTelephone(BigInteger telephone) {
        this.telephone = telephone;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getSituationmatrimoniale() {
        return situationmatrimoniale;
    }

    public void setSituationmatrimoniale(String situationmatrimoniale) {
        this.situationmatrimoniale = situationmatrimoniale;
    }

    public String getNompere() {
        return nompere;
    }

    public void setNompere(String nompere) {
        this.nompere = nompere;
    }

    public BigInteger getNumpere() {
        return numpere;
    }

    public void setNumpere(BigInteger numpere) {
        this.numpere = numpere;
    }

    public String getNommere() {
        return nommere;
    }

    public void setNommere(String nommere) {
        this.nommere = nommere;
    }

    public BigInteger getNummere() {
        return nummere;
    }

    public void setNummere(BigInteger nummere) {
        this.nummere = nummere;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        hash += (iddossier != null ? iddossier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dossieridentification)) {
            return false;
        }
        Dossieridentification other = (Dossieridentification) object;
        if ((this.iddossier == null && other.iddossier != null) || (this.iddossier != null && !this.iddossier.equals(other.iddossier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dossieridentification[ iddossier=" + iddossier + " ]";
    }
    
}
