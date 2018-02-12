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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lucien Gygli
 */
public class Quiz {

    private List<Kategorie> k;
    private List<Frage> f;
    private int score;
    private Date start;
    private boolean finished;
    private int timeGoneBy;

    public Quiz(ArrayList<Kategorie> k) {
        this.k = k;
        f = new ArrayList<>();
        for (Kategorie ka : k) {
            for (Frage fa : ka.getFrageList()) {
                f.add(fa);
            }
        }

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getTimeGoneBy() {
        return timeGoneBy;
    }

    public void setTimeGoneBy(int timeGoneBy) {
        this.timeGoneBy = timeGoneBy;
    }

}
