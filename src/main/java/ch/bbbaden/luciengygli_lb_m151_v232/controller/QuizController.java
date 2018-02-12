/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.controller;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import ch.bbbaden.luciengygli_lb_m151_v232.model.Quiz;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lucien Gygli
 */
@Named(value = "quizController")
@SessionScoped
public class QuizController implements Serializable {

    private Kategorie dropdown;
    private List<Kategorie> picked = new ArrayList<>();
    private Quiz q;

    public void addKategorie() {
        if (!picked.contains(dropdown)) {
            picked.add(dropdown);
        }
    }

    public void removeKategorie() {
        if (picked.contains(dropdown)) {
            picked.remove(dropdown);
        }
    }

    public String start() {
        if (!picked.isEmpty()) {
            q = new Quiz((ArrayList<Kategorie>) picked);
            return "prepare.xhtml";
        } else {
            return "chooseKategory.xhtml";
        }
    }

    public String getPicked() {
        if (picked.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        for (Kategorie k : picked) {
            sb.append(k.getNameK()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public void setPicked(List<Kategorie> picked) {
        this.picked = picked;
    }

    public Kategorie getDropdown() {
        return dropdown;
    }

    public void setDropdown(Kategorie dropdown) {
        this.dropdown = dropdown;
    }

}
