package Airhockey.Main;

import Airhockey.User.User;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Roel
 */
public class Lobby {

    private ArrayList<User> users;
    private Chatbox chatbox;
    private ScoreCalculator scoreCalculator;
    
    public Lobby()
    {
        chatbox = new Chatbox();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Chatbox getChatbox() {
        return chatbox;
    }

    public ScoreCalculator getScoreCalculator() {
        return scoreCalculator;
    }
}
