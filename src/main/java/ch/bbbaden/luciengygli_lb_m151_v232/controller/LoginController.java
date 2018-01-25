/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lucien Gygli
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username = "";
    private String password = "";
    private boolean loggedIn = false;

    public String doLogin() {

        if (username.equals("admin") && password.equals("admin")) {
            loggedIn = true;
            return "secured/index.xhtml";
        } else {
            loggedIn = false;
            return "index.xhtml";
        }
    }

    public String logout() {
        loggedIn = false;
        return "index.xhtml";
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
        return loggedIn;
    }

}
