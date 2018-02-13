/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lucien Gygli
 */
@Entity
@Table(name = "kategorie", catalog = "luciengygli_lb_m151_v232", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorie.findAll", query = "SELECT k FROM Kategorie k")
    , @NamedQuery(name = "Kategorie.findById", query = "SELECT k FROM Kategorie k WHERE k.id = :id")
    , @NamedQuery(name = "Kategorie.findByNameK", query = "SELECT k FROM Kategorie k WHERE k.nameK = :nameK")})
public class Kategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nameK", nullable = false, length = 30)
    private String nameK;
    @ManyToMany(mappedBy = "kategorieList", fetch = FetchType.LAZY)
    private List<Durchlauf> durchlaufList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategorieId", fetch = FetchType.LAZY)
    private List<Frage> frageList;

    public Kategorie() {
    }

    public Kategorie(Integer id) {
        this.id = id;
    }

    public Kategorie(Integer id, String nameK) {
        this.id = id;
        this.nameK = nameK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameK() {
        return nameK;
    }

    public void setNameK(String nameK) {
        this.nameK = nameK;
    }

    @XmlTransient
    public List<Durchlauf> getDurchlaufList() {
        return durchlaufList;
    }

    public void setDurchlaufList(List<Durchlauf> durchlaufList) {
        this.durchlaufList = durchlaufList;
    }

    @XmlTransient
    public List<Frage> getFrageList() {
        return frageList;
    }

    public void setFrageList(List<Frage> frageList) {
        this.frageList = frageList;
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
        if (!(object instanceof Kategorie)) {
            return false;
        }
        Kategorie other = (Kategorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameK;
    }

}
