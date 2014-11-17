package Airhockey.Main;

import javax.swing.*;
import java.awt.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author martijn
 */

public class Login extends JFrame {

    Database db;
    
    JButton blogin = new JButton("Login");
    JPanel panel = new JPanel();
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);

    Login() {
        super("Login Autentification");
        this.db = new Database();
        setSize(300, 200);
        setLocation(500, 280);
        panel.setLayout(null);

        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

    public void actionlogin() {
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = pass.getText();
                
                if (db.LoginCheckTest(puname, ppaswd)) {
                    Game game = new Game(new String[100]);
                    Lobby lobby = new Lobby();
                    lobby.getChatbox().display();
                    dispose();
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    txuser.setText("");
                    pass.setText("");
                    txuser.requestFocus();
                }

            }
        });
    }
}
