package Client;

import Client.Components.MyButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositPage extends JPanel {
    private MyButton openButton, backButton, refreshButton;
    private MainFrame frame = null;

    public DepositPage(MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        openButton = new MyButton("Open a deposit", 100, 100);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.depositPage.setVisible(false);
                frame.openDepositPage.setVisible(true);
            }
        });


        backButton = new MyButton("Back", 200, 100);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.depositPage.setVisible(false);
                frame.menuPage.setVisible(true);
            }
        });

        refreshButton = new MyButton("Refresh",300,100);


        add(openButton);
        add(backButton);
        add(refreshButton);
    }
}