/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lucien Gygli
 */
@Entity
@Table(name = "durchlauf", catalog = "luciengygli_lb_m151_v232", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Durchlauf.findAll", query = "SELECT d FROM Durchlauf d ORDER BY d.score DESC")
    , @NamedQuery(name = "Durchlauf.findById", query = "SELECT d FROM Durchlauf d WHERE d.id = :id")
    , @NamedQuery(name = "Durchlauf.findByUser", query = "SELECT d FROM Durchlauf d WHERE d.user = :user ORDER BY d.score DESC")
    , @NamedQuery(name = "Durchlauf.findByScore", query = "SELECT d FROM Durchlauf d WHERE d.score = :score")
    , @NamedQuery(name = "Durchlauf.findByFinishDate", query = "SELECT d FROM Durchlauf d WHERE d.finishDate = :finishDate")
    , @NamedQuery(name = "Durchlauf.findByTimeUsed", query = "SELECT d FROM Durchlauf d WHERE d.timeUsed = :timeUsed")})
public class Durchlauf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user", nullable = false, length = 30)
    private String user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score", nullable = false)
    private double score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finish_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeUsed", nullable = false)
    private int timeUsed;
    @JoinTable(name = "durchlauf_kategorie", joinColumns = {
        @JoinColumn(name = "durchlaufId", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "kategorieId", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Kategorie> kategorieList;
    @Transient
    private int rank;

    public Durchlauf() {
    }

    public Durchlauf(Integer id) {
        this.id = id;
    }

    public Durchlauf(Integer id, String user, int score, Date finishDate, int timeUsed) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.finishDate = finishDate;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    @XmlTransient
    public List<Kategorie> getKategorieList() {
        return kategorieList;
    }

    public void setKategorieList(List<Kategorie> kategorieList) {
        this.kategorieList = kategorieList;
    }

    public String kategorieListToString() {

        if (kategorieList.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (Kategorie kategorie : kategorieList) {
            sb.append(kategorie.getNameK()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
        return user;
    }

}
