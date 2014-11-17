/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Airhockey.Elements.Puck;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam
 */
public class PuckTest {

    public Puck puck;

    public PuckTest() {
    }

    @Before
    public void setUp() {
        //puck = new Puck(5, 10, 20);
        //Horizontal and Vertical movement are 1 as a default value
    }

    @Test
    public void testConstructor() {
        assertEquals("Position X is not correct", 10, puck.getPositionX());
        assertEquals("Position Y is not correct", 20, puck.getPositionY());
    }

    @Test
    public void testChangeHorizontalCourse() {
        puck.changeHorizontalCourse();

        assertEquals("Horizontal movementChange is not Correct", -1, puck.getHorizontalMovement());
    }

    @Test
    public void testChangeVerticalCourse() {
        puck.changeVerticalCourse();

        assertEquals("Vertical movementChange is not Correct", -1, puck.getVerticalMovement());
    }

}
