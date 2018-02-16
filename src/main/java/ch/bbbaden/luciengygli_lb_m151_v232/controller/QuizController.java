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
    private String user;
    private boolean fiftyUsed;

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
            List<Frage> f = new ArrayList<>();
            for (Kategorie k : picked) {
                f.addAll(k.getFrageList());
            }
            if (!f.isEmpty()) {

                q = new Quiz((ArrayList<Kategorie>) picked);
                index = 0;
                getFrage();
                q.startCount();
                questionsDone = 0;
                return "prepare.xhtml";
            }
        }
        return "chooseKategory.xhtml";
    }

    public String next() {
        index++;
        getFrage();
        antworten = null;
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

        if (a != null && a.equals(current.getRichtigeA())) {
            current.setRichtig(current.getRichtig() + 1);

            ff.edit(current);

            q.setScore(q.getScore() + 30);
            questionsDone++;

            if (questionsDone < q.getF().size()) {
                antworten = null;
                return next();
            } else {
                q.endCount();
                antworten = null;
                return "finished.xhtml";
            }

        } else {
            current.setFalsch(current.getFalsch() + 1);

            ff.edit(current);

            q.setScore(0);
            antworten = null;

            return "done.xhtml";
        }

    }

    public String finish() {
        createHighscore();
        picked = new ArrayList<>();
        q = null;
        fiftyUsed = false;

        return "highscores.xhtml";
    }

    public String finishEarly() {
        q.endCount();
        return "finished.xhtml";
    }

    public void createHighscore() {

        q.setDuration(q.getDuration() - 10);

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

    public int getPercentage() {
        if (current.getRichtig() + current.getFalsch() == 0) {
            return 0;
        } else {
            return (current.getRichtig() * 100 / (current.getRichtig() + current.getFalsch()));
        }
    }

    public String fifty() {
        fiftyUsed = true;
        antworten.remove(current.getFalscheA1());
        antworten.remove(current.getFalscheA3());
        return "question.xhtml";
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

    public boolean isFiftyUsed() {
        return fiftyUsed;
    }

    public void setFiftyUsed(boolean fiftyUsed) {
        this.fiftyUsed = fiftyUsed;
    }

    public Quiz getQ() {
        return q;
    }

    public void setQ(Quiz q) {
        this.q = q;
    }

}
