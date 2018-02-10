/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.controller;

import ch.bbbaden.luciengygli_lb_m151_v232.controller.util.PaginationHelper;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Durchlauf;
import ch.bbbaden.luciengygli_lb_m151_v232.facade.HighscoreFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

/**
 *
 * @author Lucien Gygli
 */
@Named("highscoreController")
@SessionScoped
public class HighscoreController implements Serializable {

    private static final long serialVersionUID = 1L;

    private DataModel items = null;
    private String userNow;
    private PaginationHelper pagination;
    @EJB
    private ch.bbbaden.luciengygli_lb_m151_v232.facade.HighscoreFacade ejbFacade;
    private List<Durchlauf> list;

    public HighscoreController() {
        userNow = null;
        list = null;
    }

    public List<Durchlauf> getDruchlaufe() {
        List<Durchlauf> ds = ejbFacade.getRanking();

        for (Durchlauf d : ds) {
            ejbFacade.addRank(d);
        }

        return ds;
    }

    public List<Durchlauf> getDurchlaufsVon() {
        List<Durchlauf> ds = ejbFacade.getDruchlaufVon(userNow);

        for (Durchlauf d : ds) {
            ejbFacade.addRank(d);
        }

        return ds;
    }

    public String reload() {
        if (userNow != null) {
            list = ejbFacade.getDurchlauf(userNow);
        }
        return "highscores.xhtml";
    }

    public boolean isUser() {
        return !ejbFacade.getDurchlauf(userNow).isEmpty();
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    private HighscoreFacade getFacade() {
        return ejbFacade;
    }

    public String getUserNow() {
        return userNow;
    }

    public void setUserNow(String userNow) {
        this.userNow = userNow;
    }

}
