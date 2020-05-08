package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferPage extends JPanel {
    private MyLabel toLabel, nameLabel, surnameLabel, nLabel, sLabel, sumLabel;
    private MyTextField toField, sumField;
    private MyButton okButton, historyButton, backButton, confirmButton;

    private MainFrame frame = null;

    public TransferPage(MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        toLabel = new MyLabel("Transfer to",100,100);
        toField = new MyTextField(200,100);
        okButton = new MyButton("Ok",100,175);
        historyButton = new MyButton("History",200,175);
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.transferPage.setVisible(false);
                frame.historyPage.setVisible(true);
            }
        });

        backButton = new MyButton("Back",300,175);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.transferPage.setVisible(false);
                frame.menuPage.setVisible(true);
            }
        });

        nameLabel = new MyLabel("Name",100,250);
        nLabel = new MyLabel("BD",200,250);
        surnameLabel = new MyLabel("Surname",100,325);
        sLabel = new MyLabel("BD",200,325);
        sumLabel = new MyLabel("Sum",100,400);
        sumField = new MyTextField(200,400);
        confirmButton = new MyButton("Confirm",150,475);

        add(toLabel);
        add(toField);
        add(okButton);
        add(historyButton);
        add(backButton);
        add(nameLabel);
        add(surnameLabel);
        add(nLabel);
        add(sLabel);
        add(sumLabel);
        add(sumField);
        add(confirmButton);
    }
}
