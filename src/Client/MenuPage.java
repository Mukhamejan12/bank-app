package Client;

import Client.Components.MyButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JPanel {
    private MyButton profileButton, transferButton, creditButton, depositButton, replenishButton, exitButton;
    private MainFrame frame = null;

    public MenuPage(MainFrame frame){
        this.frame = frame;
        setSize(500,800);
        setLayout(null);

        profileButton = new MyButton("Profile",250,100);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.menuPage.setVisible(false);
                frame.profilePage.setVisible(true);
            }
        });
        transferButton = new MyButton("Transfer",250,200);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.menuPage.setVisible(false);
                frame.transferPage.setVisible(true);
            }
        });

        creditButton = new MyButton("Credit",250,300);
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.menuPage.setVisible(false);
                frame.creditPage.setVisible(true);
            }
        });

        depositButton = new MyButton("Deposit",250,400);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.menuPage.setVisible(false);
                frame.depositPage.setVisible(true);
            }
        });

        replenishButton = new MyButton("Replenish",250,500);
        replenishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.menuPage.setVisible(false);
                frame.replenishPage.setVisible(true);
            }
        });

        exitButton = new MyButton("Exit", 250,600);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.menuPage.setVisible(false);
                frame.loginPage.setVisible(true);
            }
        });

        add(profileButton);
        add(transferButton);
        add(creditButton);
        add(depositButton);
        add(replenishButton);
        add(exitButton);
    }
}
