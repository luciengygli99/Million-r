/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.model;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Frage;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lucien Gygli
 */
public class Quiz {

    private Kategorie k;
    private List<Frage> f;
    private int score;
    private Date start;

}
