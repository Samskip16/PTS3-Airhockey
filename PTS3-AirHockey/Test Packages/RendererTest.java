/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Airhockey.Main.Renderer;
import Airhockey.Elements.Bat;
import Airhockey.Main.Game;
import Airhockey.Elements.Puck;
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
public class RendererTest {

    public Renderer renderer;
    public Puck puck;
    public Game game;
    public Bat bat;

    public RendererTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
       // renderer = new Renderer();
        //puck = new Puck(1, 10, 20); //20 = upper left corner of the incapsulating rectangle of the puck
       // bat = new Bat(Color.BLUE, 10, 10, 1);
    }

    @Test
    public void testMovePuck() {
        renderer.movePuck();
        //assertEquals();
    }

    @Test
    public void testDetermineUpdateRate() {
        renderer.determineUpdateRate();
        //assertEquals(350, game.getUPS());
    }

    @Test
    public void testBatCollision() {
        if (puck.getVerticalMovement() == 1) {
            renderer.movePuck();
            renderer.checkBatCollision();
            assertEquals("Puck not bouncing correctly off the bat", -1, puck.getVerticalMovement());
        } else if (puck.getVerticalMovement() == -1) {
            renderer.movePuck();
            renderer.checkBatCollision();
            assertEquals("Puck not bouncing correctly off the bat", 1, puck.getVerticalMovement());
        }
    }
}
