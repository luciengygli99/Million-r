/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.controller;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Antwort;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Durchlauf;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Frage;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import ch.bbbaden.luciengygli_lb_m151_v232.facade.FrageFacade;
import ch.bbbaden.luciengygli_lb_m151_v232.facade.HighscoreFacade;
import ch.bbbaden.luciengygli_lb_m151_v232.model.Quiz;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javafx.print.Collation;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lucien Gygli
 */
@Named(value = "quizController")
@SessionScoped
public class QuizController implements Serializable {

    @EJB
    private FrageFacade ff;
    @EJB
    private HighscoreFacade df;

    private Kategorie dropdown;
    private List<Kategorie> picked = new ArrayList<>();
    private int index = 0;
    private List<Antwort> antworten = null;
    private Quiz q;
    private Frage current;
    private int questionsDone = 0;
    private Date start;
    private String user;

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
            getFrage();
            q.startCount();
            start = new Date();
            return "prepare.xhtml";
        } else {
            return "chooseKategory.xhtml";
        }
    }

    public String next() {
        index++;
        getFrage();
        antworten = null;
        start = new Date();
        return "question.xhtml";
    }

    public void getFrage() {
        current = q.getF().get(index);
    }

    public List<Antwort> getRandomAntwort() {

        if (antworten != null) {
            return antworten;
        }

        antworten = new ArrayList<>();

        antworten.add(current.getRichtigeA());
        antworten.add(current.getFalscheA1());
        antworten.add(current.getFalscheA2());
        antworten.add(current.getFalscheA3());

        Collections.shuffle(antworten);

        return antworten;
    }

    public String antworten(Antwort a) {

        if (a != null && a == current.getRichtigeA() && new Date().getTime() - start.getTime() <= 30000) {
            current.setRichtig(current.getRichtig() + 1);

            ff.edit(current);

            q.setScore(q.getScore() + 30);
            questionsDone++;

            if (questionsDone < q.getF().size()) {
                return next();
            } else {
                q.endCount();
                return "finished.xhtml";
            }

        } else {
            current.setFalsch(current.getFalsch() + 1);

            ff.edit(current);

            q.setScore(0);

            return "done.xhtml";
        }

    }

    public String finish() {
        createHighscore();
        picked = new ArrayList<>();
        q = null;
        start = null;
        index = 0;

        return "index.xhtml";
    }

    public void createHighscore() {

        Durchlauf d = new Durchlauf();

        d.setKategorieList(picked);
        d.setScore((double) q.getScore() / (double) q.getDuration());
        d.setFinishDate(new Date());
        d.setTimeUsed(q.getDuration());
        d.setUser(user);

        df.create(d);

    }

    public void destroy(Durchlauf d) {

        df.remove(d);

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

    public Frage getCurrent() {
        return current;
    }

    public void setCurrent(Frage current) {
        this.current = current;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
