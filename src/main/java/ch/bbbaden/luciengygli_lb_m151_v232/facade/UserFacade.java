/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.facade;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lucien Gygli
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ch.bbbaden_LucienGygli_LB_M151_V232_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User checkLogin(String username, String pw) {
        Query q = em.createNamedQuery("User.checkLogin", User.class);
        q.setParameter("username", username);
        q.setParameter("pw", pw);

        User u;
        try {
            u = (User) q.getSingleResult();
        } catch (Exception ex) {
            u = null;
        }
        return u;
    }
}
