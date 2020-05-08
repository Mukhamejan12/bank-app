package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormalizeCreditPage extends JPanel {
    private MyLabel sumLabel, monthLabel, jobLabel, incomeLabel, numberLabel;
    private MyTextField sumField, incomeField, numberField;
    private JComboBox monthBox;
    private JCheckBox jobBox;
    private MyButton okButton, backButton;
    private String month[] = {"", "6 month", "12 month", "36 month"};
    private MainFrame frame = null;

    public FormalizeCreditPage (MainFrame frame) {
        setSize(800,600);
        setLayout(null);
        this.frame = frame;

        sumLabel = new MyLabel("Total money",100,100);
        sumField = new MyTextField(300,100);
        monthLabel = new MyLabel("Number of month",100,175);
        monthBox = new JComboBox(month);
        monthBox.setBounds(300,175,100,30);

        jobLabel = new MyLabel("Have you a job?",100,250);
        jobBox = new JCheckBox();
        jobBox.setBounds(300,250,150,30);
        incomeLabel = new MyLabel("Income of the family",100,325);
        incomeField = new MyTextField(300,325);
        numberLabel = new MyLabel("Number of people",100,400);
        numberField = new MyTextField(300,400);


        okButton = new MyButton("OK",300,475);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!jobBox.isSelected()){
                }
                else if(sumField.getText().isEmpty() || incomeField.getText().isEmpty() || numberField.getText().isEmpty() || monthBox.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(frame,"Fill all fields");
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Credit is rejected" );
                }
            }
        });


        backButton = new MyButton("Back",150,475);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.formalizeCreditPage.setVisible(false);
                frame.creditPage.setVisible(true);
            }
        });

        add(sumField);
        add(sumLabel);
        add(monthLabel);
        add(monthBox);
        add(okButton);
        add(backButton);
        add(jobLabel);
        add(jobBox);
        add(incomeLabel);
        add(incomeField);
        add(numberLabel);
        add(numberField);
    }
}
