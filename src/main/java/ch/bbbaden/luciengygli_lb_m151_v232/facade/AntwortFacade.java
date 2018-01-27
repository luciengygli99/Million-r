/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.facade;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Antwort;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lucien Gygli
 */
@Stateless
public class AntwortFacade extends AbstractFacade<Antwort> {

    @PersistenceContext(unitName = "ch.bbbaden_LucienGygli_LB_M151_V232_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntwortFacade() {
        super(Antwort.class);
    }

}
