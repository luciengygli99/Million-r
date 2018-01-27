/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.facade;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lucien Gygli
 */
@Stateless
public class KategorieFacade extends AbstractFacade<Kategorie> {

    @PersistenceContext(unitName = "ch.bbbaden_LucienGygli_LB_M151_V232_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KategorieFacade() {
        super(Kategorie.class);
    }

}
