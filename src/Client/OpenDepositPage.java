package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenDepositPage extends JPanel{
    private JComboBox monthBox;
    private MyLabel monthLabel, sumLabel, percentLabel;
    private MyTextField sumField;
    private MyButton backButton, okButton;
    private String month[] = {"Number of month", "6 month", "12 month", "36 month"};
    private MainFrame frame = null;

    public OpenDepositPage(MainFrame frame) {
        this.frame = frame;
        setSize(800,600);
        setLayout(null);

        sumLabel = new MyLabel("Total money", 100,200);
        sumField = new MyTextField(300,200);
        monthLabel = new MyLabel("Number of month",100,350);
        monthBox = new JComboBox(month);
        monthBox.setBounds(300,350,100,30);
        percentLabel = new MyLabel("12%",200,450);
        backButton = new MyButton("back",100,550);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.openDepositPage.setVisible(false);
                frame.depositPage.setVisible(true);
            }
        });
        okButton = new MyButton("OK",300,350);

        add(sumLabel);
        add(sumField);
        add(monthLabel);
        add(monthBox);
        add(percentLabel);
        add(backButton);
        add(okButton);
    }
}
