/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "antwort")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Antwort.findAll", query = "SELECT a FROM Antwort a")
    , @NamedQuery(name = "Antwort.findById", query = "SELECT a FROM Antwort a WHERE a.id = :id")
    , @NamedQuery(name = "Antwort.findByTextA", query = "SELECT a FROM Antwort a WHERE a.textA = :textA")
    , @NamedQuery(name = "Antwort.findByFalsch", query = "SELECT a FROM Antwort a WHERE a.falsch = :falsch")
    , @NamedQuery(name = "Antwort.findByRichtig", query = "SELECT a FROM Antwort a WHERE a.richtig = :richtig")})
public class Antwort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "textA")
    private String textA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "falsch")
    private int falsch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "richtig")
    private int richtig;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "falscheA1")
    private Collection<Frage> frageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "falscheA2")
    private Collection<Frage> frageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "falscheA3")
    private Collection<Frage> frageCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "richtigeA")
    private Collection<Frage> frageCollection3;

    public Antwort() {
    }

    public Antwort(Integer id) {
        this.id = id;
    }

    public Antwort(Integer id, String textA, int falsch, int richtig) {
        this.id = id;
        this.textA = textA;
        this.falsch = falsch;
        this.richtig = richtig;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextA() {
        return textA;
    }

    public void setTextA(String textA) {
        this.textA = textA;
    }

    public int getFalsch() {
        return falsch;
    }

    public void setFalsch(int falsch) {
        this.falsch = falsch;
    }

    public int getRichtig() {
        return richtig;
    }

    public void setRichtig(int richtig) {
        this.richtig = richtig;
    }

    @XmlTransient
    public Collection<Frage> getFrageCollection() {
        return frageCollection;
    }

    public void setFrageCollection(Collection<Frage> frageCollection) {
        this.frageCollection = frageCollection;
    }

    @XmlTransient
    public Collection<Frage> getFrageCollection1() {
        return frageCollection1;
    }

    public void setFrageCollection1(Collection<Frage> frageCollection1) {
        this.frageCollection1 = frageCollection1;
    }

    @XmlTransient
    public Collection<Frage> getFrageCollection2() {
        return frageCollection2;
    }

    public void setFrageCollection2(Collection<Frage> frageCollection2) {
        this.frageCollection2 = frageCollection2;
    }

    @XmlTransient
    public Collection<Frage> getFrageCollection3() {
        return frageCollection3;
    }

    public void setFrageCollection3(Collection<Frage> frageCollection3) {
        this.frageCollection3 = frageCollection3;
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
        if (!(object instanceof Antwort)) {
            return false;
        }
        Antwort other = (Antwort) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.bbbaden.luciengygli_lb_m151_v232.entity.Antwort[ id=" + id + " ]";
    }

}
