/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.controller;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.User;
import ch.bbbaden.luciengygli_lb_m151_v232.facade.UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucien Gygli
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username = "";
    private String password = "";

    private User u;
    @EJB
    private UserFacade uf;

    public String doLogin() {
        u = uf.checkLogin(username, password);

        if (u != null) {
            return "secured/index.xhtml?faces-redirect=true";
        } else {
            return "index.xhtml";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        u = null;
        return "/index?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return u != null;
    }

}
