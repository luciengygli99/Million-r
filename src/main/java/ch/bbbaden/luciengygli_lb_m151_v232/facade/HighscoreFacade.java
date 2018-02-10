/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.facade;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Durchlauf;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lucien Gygli
 */
@Stateless
public class HighscoreFacade extends AbstractFacade<Durchlauf> {

    @PersistenceContext(unitName = "ch.bbbaden_LucienGygli_LB_M151_V232_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HighscoreFacade() {
        super(Durchlauf.class);
    }

    public List<Durchlauf> getRanking() {
        Query query = em.createQuery("SELECT d FROM Durchlauf d ORDER BY d.score DESC");
        List<Durchlauf> d = query.getResultList();
        for (Durchlauf du : d) {
            addRank(du);
        }
        return d;
    }

    public List<Durchlauf> getDruchlaufVon(String name) {
        Query query = em.createNamedQuery("Durchlauf.findByUser");
        query.setParameter("user", name);

        return query.getResultList();
    }

    public void addRank(Durchlauf d) {
        Query query = em.createQuery("SELECT COUNT(d) FROM Durchlauf d WHERE d.score > :s");
        query.setParameter("s", d.getScore());
        d.setRank(Integer.valueOf(String.valueOf(query.getSingleResult())) + 1);
    }

    public List<Durchlauf> getDurchlauf(String user) {
        Query q = em.createQuery("SELECT d FROM Durchlauf d WHERE d.user = :user ORDER BY d.score DESC", Durchlauf.class);
        q.setParameter("user", user);

        List<Durchlauf> durchlauf = new ArrayList<>();

        try {
            durchlauf = q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return durchlauf;
    }
}
