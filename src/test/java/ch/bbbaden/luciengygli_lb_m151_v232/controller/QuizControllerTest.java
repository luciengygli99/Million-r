/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.controller;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import ch.bbbaden.luciengygli_lb_m151_v232.model.Quiz;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucien Gygli
 */
public class QuizControllerTest {

    public QuizControllerTest() {
    }

    @Test
    public void testFinishEarly() throws InterruptedException {
        //Arrange
        Quiz q = new Quiz(Arrays.asList(new Kategorie[]{}));
        QuizController qc = new QuizController();
        qc.setQ(q);
        q.setQuizStarted(10);
        String fun;

        //Act
        fun = qc.finishEarly();
        sleep(1000);

        //Assert
        assertNotNull(qc.getQ().getDuration());
        assertEquals("finished.xhtml", fun);
    }

}
