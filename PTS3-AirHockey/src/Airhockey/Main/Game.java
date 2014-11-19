package Airhockey.Main;

import Airhockey.Elements.Bat;
import Airhockey.User.Player;
import Airhockey.User.Spectator;
import Airhockey.User.User;
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

    private int id;
    private int round = 10;
    private Player owner;
    private Renderer renderer;
    private ArrayList<Player> players;
    private ArrayList<Spectator> spectators;
    private Result result;
    private Chatbox chatbox;
    private ScoreCalculator scoreCalculator;

    public Game(Renderer renderer) {
        this.renderer = renderer;
        players = new ArrayList<>();
        addPlayer(new Player(1));
        addPlayer(new Player(2));
        addPlayer(new Player(3));
        //scoreCalculator = new ScoreCalculator(player1ScoreLabel, player2ScoreLabel, player3ScoreLabel);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame(Player owner) {
        throw new UnsupportedOperationException();
    }

    public void leaveGame(User user) {
        throw new UnsupportedOperationException();
    }

    public void addBatToPlayer(int playerId, Bat bat) {
        for (Player player : players) {
            if (player.getId() == playerId) {
                player.setBat(bat);
            }
        }
    }

    public void setGoal(Bat batWhoMadeGoal, Bat batWhoFailed) {
        for (Player player : players) {
            if (player.getBat() == batWhoMadeGoal) {
                player.upScore();
                renderer.setTextFields("PLAYER" + player.getId() + "_SCORE", player.getScore() + "");
            } else if (player.getBat() == batWhoFailed) {
                player.downScore();
                renderer.setTextFields("PLAYER" + player.getId() + "_SCORE", player.getScore() + "");
            }
        }

        round--;
        if (round == 0) {
            stop();
        }
    }

    private void stop() {

    }
}
