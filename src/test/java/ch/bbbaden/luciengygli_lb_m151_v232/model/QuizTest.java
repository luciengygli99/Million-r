/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.luciengygli_lb_m151_v232.model;

import ch.bbbaden.luciengygli_lb_m151_v232.entity.Frage;
import ch.bbbaden.luciengygli_lb_m151_v232.entity.Kategorie;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucien Gygli
 */
public class QuizTest {

    public QuizTest() {
    }

    @Test
    public void testStartCount() {
        //Arrange
        Quiz quiz = new Quiz(Arrays.asList(new Kategorie[]{}));

        //Act
        quiz.startCount();
        //Assert
        assertTrue(quiz.getQuizStarted() == System.currentTimeMillis());
    }

    @Test
    public void testEndCount() {
        //Arrange
        Quiz quiz = new Quiz(Arrays.asList(new Kategorie[]{}));

        //Act
        quiz.endCount();

        //Assert
        assertTrue(quiz.getQuizEnded() == System.currentTimeMillis());
    }

    @Test
    public void testCalculateDuraion() throws InterruptedException {
        //Arrange
        Quiz quiz = new Quiz(Arrays.asList(new Kategorie[]{}));
        quiz.startCount();
        sleep(1000);
        quiz.endCount();

        //Act
        quiz.calculateDuration();

        //Assert
        assertTrue(quiz.getDuration() == 1);
    }

}
