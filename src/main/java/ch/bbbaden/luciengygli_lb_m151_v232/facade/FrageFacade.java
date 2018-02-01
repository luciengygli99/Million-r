/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.facade;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Frage;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
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
public class FrageFacade extends AbstractFacade<Frage> {

    @PersistenceContext(unitName = "ch.bbbaden_LucienGygli_LB_M151_V232_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrageFacade() {
        super(Frage.class);
    }

    public List<Frage> getFragen(Kategorie kategorie_id) {
        Query q = em.createQuery("SELECT f FROM Frage f WHERE f.kategorieId = :kategorie_id", Frage.class);
        q.setParameter("kategorie_id", kategorie_id);

        List<Frage> fragen = new ArrayList<>();

        try {
            fragen = q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return fragen;
    }

}
