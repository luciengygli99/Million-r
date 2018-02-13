/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.model;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Frage;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lucien Gygli
 */
public class Quiz {

    private List<Kategorie> k;
    private List<Frage> f;
    private int score;
    private Date taken;
    private boolean finished;
    private int duration;
    private long quizStarted;
    private long quizEnded;

    public Quiz(ArrayList<Kategorie> k) {
        this.k = k;
        f = new ArrayList<>();
        for (Kategorie ka : k) {
            f.addAll(ka.getFrageList());
        }
        Collections.shuffle(f);
        if (f.size() >= 10) {
            f = f.subList(0, 9);
        }
    }

    public void startCount() {
        quizStarted = System.currentTimeMillis();
    }

    public void endCount() {
        quizEnded = System.currentTimeMillis();
        calculateDuration();
    }

    public void calculateDuration() {
        duration = (int) ((quizEnded - quizStarted) / 1000);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTaken() {
        return taken;
    }

    public void setTaken(Date taken) {
        this.taken = taken;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Frage> getF() {
        return f;
    }

}
