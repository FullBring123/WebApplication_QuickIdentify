/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Windows8.1
 */
@Entity
@Table(name = "agentcallcenter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agentcallcenter.findAll", query = "SELECT a FROM Agentcallcenter a")
    , @NamedQuery(name = "Agentcallcenter.findById", query = "SELECT a FROM Agentcallcenter a WHERE a.id = :id")
    , @NamedQuery(name = "Agentcallcenter.nextId", query = "SELECT MAX(a.id) FROM Agentcallcenter a")
    , @NamedQuery(name = "Agentcallcenter.findByNom", query = "SELECT a FROM Agentcallcenter a WHERE a.nom = :nom")
    , @NamedQuery(name = "Agentcallcenter.findByPrenom", query = "SELECT a FROM Agentcallcenter a WHERE a.prenom = :prenom")
    , @NamedQuery(name = "Agentcallcenter.findByDatenais", query = "SELECT a FROM Agentcallcenter a WHERE a.datenais = :datenais")
    , @NamedQuery(name = "Agentcallcenter.findByLieunais", query = "SELECT a FROM Agentcallcenter a WHERE a.lieunais = :lieunais")
    , @NamedQuery(name = "Agentcallcenter.findBySexe", query = "SELECT a FROM Agentcallcenter a WHERE a.sexe = :sexe")
    , @NamedQuery(name = "Agentcallcenter.findByFonction", query = "SELECT a FROM Agentcallcenter a WHERE a.fonction = :fonction")
    , @NamedQuery(name = "Agentcallcenter.findByLieuservice", query = "SELECT a FROM Agentcallcenter a WHERE a.lieuservice = :lieuservice")
    , @NamedQuery(name = "Agentcallcenter.findByMatricule", query = "SELECT a FROM Agentcallcenter a WHERE a.matricule = :matricule")
    , @NamedQuery(name = "Agentcallcenter.findByEtat", query = "SELECT a FROM Agentcallcenter a WHERE a.etat = :etat")
    , @NamedQuery(name = "Agentcallcenter.findByLogin", query = "SELECT a FROM Agentcallcenter a WHERE a.login = :login")
    , @NamedQuery(name = "Agentcallcenter.findByPassword", query = "SELECT a FROM Agentcallcenter a WHERE a.password = :password")
    , @NamedQuery(name = "Agentcallcenter.findByLoginPassword", query = "SELECT a FROM Agentcallcenter a WHERE a.login = :login AND a.password = :password")})
public class Agentcallcenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Column(name = "sexe")
    private String sexe;
    @Size(max = 254)
    @Column(name = "fonction")
    private String fonction;
    @Size(max = 254)
    @Column(name = "lieuservice")
    private String lieuservice;
    @Size(max = 254)
    @Column(name = "matricule")
    private String matricule;
    @Size(max = 254)
    @Column(name = "etat")
    private String etat;
    @Column(name = "telephone")
    private Integer telephone;
    @Size(max = 254)
    @Column(name = "login")
    private String login;
    @Size(max = 254)
    @Column(name = "password")
    private String password;
    @JoinTable(name = "acceder", joinColumns = {
        @JoinColumn(name = "id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")})
    @ManyToMany
    private Collection<Menu> menuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private Collection<Operation> operationCollection;

    public Agentcallcenter() {
    }

    public Agentcallcenter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getLieuservice() {
        return lieuservice;
    }

    public void setLieuservice(String lieuservice) {
        this.lieuservice = lieuservice;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @XmlTransient
    public Collection<Operation> getOperationCollection() {
        return operationCollection;
    }

    public void setOperationCollection(Collection<Operation> operationCollection) {
        this.operationCollection = operationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agentcallcenter)) {
            return false;
        }
        Agentcallcenter other = (Agentcallcenter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Agentcallcenter[ id=" + id + " ]";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
    
}
