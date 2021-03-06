/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucien Gygli
 */
@Entity
@Table(name = "frage", catalog = "luciengygli_lb_m151_v232", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frage.findAll", query = "SELECT f FROM Frage f")
    , @NamedQuery(name = "Frage.findById", query = "SELECT f FROM Frage f WHERE f.id = :id")
    , @NamedQuery(name = "Frage.findByFrage", query = "SELECT f FROM Frage f WHERE f.frage = :frage")
    , @NamedQuery(name = "Frage.findByRichtig", query = "SELECT f FROM Frage f WHERE f.richtig = :richtig")
    , @NamedQuery(name = "Frage.findByFalsch", query = "SELECT f FROM Frage f WHERE f.falsch = :falsch")})
public class Frage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "frage", nullable = false, length = 100)
    private String frage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "richtig", nullable = false)
    private int richtig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "falsch", nullable = false)
    private int falsch;
    @JoinColumn(name = "kategorie_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Kategorie kategorieId;
    @JoinColumn(name = "falscheA1", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Antwort falscheA1;
    @JoinColumn(name = "falscheA2", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Antwort falscheA2;
    @JoinColumn(name = "falscheA3", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Antwort falscheA3;
    @JoinColumn(name = "richtigeA", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Antwort richtigeA;

    public Frage() {
    }

    public Frage(Integer id) {
        this.id = id;
    }

    public Frage(Integer id, String frage, int richtig, int falsch) {
        this.id = id;
        this.frage = frage;
        this.richtig = richtig;
        this.falsch = falsch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public int getRichtig() {
        return richtig;
    }

    public void setRichtig(int richtig) {
        this.richtig = richtig;
    }

    public int getFalsch() {
        return falsch;
    }

    public void setFalsch(int falsch) {
        this.falsch = falsch;
    }

    public Kategorie getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(Kategorie kategorieId) {
        this.kategorieId = kategorieId;
    }

    public Antwort getFalscheA1() {
        return falscheA1;
    }

    public void setFalscheA1(Antwort falscheA1) {
        this.falscheA1 = falscheA1;
    }

    public Antwort getFalscheA2() {
        return falscheA2;
    }

    public void setFalscheA2(Antwort falscheA2) {
        this.falscheA2 = falscheA2;
    }

    public Antwort getFalscheA3() {
        return falscheA3;
    }

    public void setFalscheA3(Antwort falscheA3) {
        this.falscheA3 = falscheA3;
    }

    public Antwort getRichtigeA() {
        return richtigeA;
    }

    public void setRichtigeA(Antwort richtigeA) {
        this.richtigeA = richtigeA;
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
        if (!(object instanceof Frage)) {
            return false;
        }
        Frage other = (Frage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.bbbaden.luciengygli_lb_m151_v232.entity.Frage[ id=" + id + " ]";
    }

}
