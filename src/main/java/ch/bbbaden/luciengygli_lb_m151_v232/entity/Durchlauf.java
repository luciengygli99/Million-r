/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucien Gygli
 */
@Entity
@Table(name = "durchlauf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Durchlauf.findAll", query = "SELECT d FROM Durchlauf d ORDER BY d.score DESC")
    , @NamedQuery(name = "Durchlauf.findById", query = "SELECT d FROM Durchlauf d WHERE d.id = :id")
    , @NamedQuery(name = "Durchlauf.findByUser", query = "SELECT d FROM Durchlauf d WHERE d.user = :user ORDER BY d.score DESC")
    , @NamedQuery(name = "Durchlauf.findByScore", query = "SELECT d FROM Durchlauf d WHERE d.score = :score")
    , @NamedQuery(name = "Druchlauf.findByFinish_date", query = "SELECT d FROM Durchlauf d WHERE d.finish_date = :finish_date")})
public class Durchlauf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "finish_date")
    private Date finish_date;
    @NotNull
    @Column(name = "timeUsed")
    private int timeUsed;
    @JoinColumn(name = "kategorie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Kategorie kategorieId;
    @Transient
    private int rank;

    public Durchlauf() {
    }

    public Durchlauf(Integer id) {
        this.id = id;
    }

    public Durchlauf(Integer id, String user, int score, Date finish_Date, int timeUsed) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.finish_date = finish_Date;
        this.timeUsed = timeUsed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Kategorie getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(Kategorie kategorieId) {
        this.kategorieId = kategorieId;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
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
        if (!(object instanceof Durchlauf)) {
            return false;
        }
        Durchlauf other = (Durchlauf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.bbbaden.luciengygli_lb_m151_v232.entity.Durchlauf[ id=" + id + " ]";
    }

}
