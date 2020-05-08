package Client;

import Client.Components.MyButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditPage extends JPanel {
    private MyButton formalizeButton, backButton, refreshButton;
    private MainFrame frame = null;

    public CreditPage(MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        formalizeButton = new MyButton("Formalize credit", 100,100);
        formalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.creditPage.setVisible(false);
                frame.formalizeCreditPage.setVisible(true);
            }
        });


        backButton = new MyButton("Back",200,100);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.creditPage.setVisible(false);
                frame.menuPage.setVisible(true);
            }
        });

        refreshButton = new MyButton("Refresh",300,100);

        add(formalizeButton);
        add(backButton);
        add(refreshButton);
    }
}
