/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Airhockey.Elements.Bat;
import java.awt.Color;
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
public class BatTest {

    public Bat bat;

    public BatTest() {

    }

    @Before
    public void setUp() {
        //bat = new Bat(Color.BLUE, 10, 10, 1);

    }

    @Test
    public void testConstructor() {
        assertEquals("Position X is not correct", 10, bat.getPositionX());
        assertEquals("Position Y is not correct", 20, bat.getPositionY());
    }

    @Test
    public void testBatMoveLeft() {
        bat.moveLeft();

        assertEquals("Movement left is wrong", 9, bat.getPositionX());
    }

    @Test
    public void testBatMoveRight() {
        bat.moveLeft();

        assertEquals("Movement right is wrong", 11, bat.getPositionX());
    }
}
