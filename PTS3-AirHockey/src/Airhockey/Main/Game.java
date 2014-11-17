package Airhockey.Main;

import Airhockey.User.Spectator;
import Airhockey.User.User;
import Airhockey.User.Player;
import java.util.ArrayList;
import javafx.application.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Roel
 */
public class Game {

    private int numberOfRoundsPlayed;
    private int id;
    private Player owner;
    private Renderer renderer;
    private ArrayList<Player> players;
    private ArrayList<Spectator> spectators;
    private Result result;
    private Chatbox chatbox;
    private ScoreCalculator scoreCalculator;

    public Game(Renderer renderer) {
        this.renderer = renderer;
        //scoreCalculator = new ScoreCalculator(player1ScoreLabel, player2ScoreLabel, player3ScoreLabel);
    }

    public void startGame() {
        //renderer.startGame();
        //startButton.setEnabled(false);
    }

    public void startGame(Player owner) {
        throw new UnsupportedOperationException();
    }

    public void leaveGame(User user) {
        throw new UnsupportedOperationException();
    }

    public void setGoal(Player player) {
        player.setScore();
        //player1ScoreLabel.setText("Player1: " + player.getScore());
        //startButton.setEnabled(true);
    }
}
