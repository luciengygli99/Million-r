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
@Table(name = "antwort", catalog = "luciengygli_lb_m151_v232", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Antwort.findAll", query = "SELECT a FROM Antwort a")
    , @NamedQuery(name = "Antwort.findById", query = "SELECT a FROM Antwort a WHERE a.id = :id")
    , @NamedQuery(name = "Antwort.findByTextA", query = "SELECT a FROM Antwort a WHERE a.textA = :textA")})
public class Antwort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "textA", nullable = false, length = 30)
    private String textA;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "falscheA1", fetch = FetchType.LAZY)
    private List<Frage> frageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "falscheA2", fetch = FetchType.LAZY)
    private List<Frage> frageList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "falscheA3", fetch = FetchType.LAZY)
    private List<Frage> frageList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "richtigeA", fetch = FetchType.LAZY)
    private List<Frage> frageList3;

    public Antwort() {
    }

    public Antwort(Integer id) {
        this.id = id;
    }

    public Antwort(Integer id, String textA) {
        this.id = id;
        this.textA = textA;
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

    @XmlTransient
    public List<Frage> getFrageList() {
        return frageList;
    }

    public void setFrageList(List<Frage> frageList) {
        this.frageList = frageList;
    }

    @XmlTransient
    public List<Frage> getFrageList1() {
        return frageList1;
    }

    public void setFrageList1(List<Frage> frageList1) {
        this.frageList1 = frageList1;
    }

    @XmlTransient
    public List<Frage> getFrageList2() {
        return frageList2;
    }

    public void setFrageList2(List<Frage> frageList2) {
        this.frageList2 = frageList2;
    }

    @XmlTransient
    public List<Frage> getFrageList3() {
        return frageList3;
    }

    public void setFrageList3(List<Frage> frageList3) {
        this.frageList3 = frageList3;
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
        return textA;
    }

}
