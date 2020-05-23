package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyPasswordField;
import Client.Components.MyTextField;
import Data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JPanel {
    private MyLabel nameLabel, surnameLabel, logLabel, pasLabel, repasLabel;
    private MyTextField nameField, surnameField, logField;
    private MyPasswordField pasField, repasField;
    private JCheckBox checkBox;
    private MyButton signUpButton, backButton, exitButton;
    private MainFrame frame = null;


    public SignUpPage(MainFrame frame){
        this.frame = frame;
        setSize(800,800);
        setLayout(null);

        nameLabel = new MyLabel("Name",100,100);
        surnameLabel = new MyLabel("Surname", 100,200);
        logLabel = new MyLabel("Login",100,300);
        pasLabel = new MyLabel("Password",100,400);
        repasLabel = new MyLabel("Repeat your password", 100,500);

        nameField = new MyTextField(200,100);
        surnameField = new MyTextField(200,200);
        logField = new MyTextField(200,300);
        pasField = new MyPasswordField(200,400);
        repasField = new MyPasswordField(200,500);

        checkBox = new JCheckBox("Show password");
        checkBox.setBounds(400,500,150,30);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBox.isSelected()){
                    pasField.setEchoChar((char)0);
                    repasField.setEchoChar((char)0);
                }
                else{
                    pasField.setEchoChar('•');
                    repasField.setEchoChar('•');
                }
            }
        });

        signUpButton = new MyButton("Sign up",100,600);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().isEmpty() || surnameField.getText().isEmpty() || logField.getText().isEmpty() || pasField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Fill all fields");
                }
                else if(pasField.getText().compareTo(repasField.getText()) != 0){
                    JOptionPane.showMessageDialog(frame,"You have incorrect password!!!" );
                }
                else{
                    User u = new User();
                    u.setName(nameField.getText());
                    u.setSurname(surnameField.getText());
                    u.setLogin(logField.getText());
                    u.setPassword(pasField.getText());

                    boolean registered = frame.clientSocket.toRegister(u);
                    if(registered == true){
                        JOptionPane.showMessageDialog(frame, "You are registered");
                        logField.setText("");
                        nameField.setText("");
                        surnameField.setText("");
                        pasField.setText("");

                        frame.signUpPage.setVisible(false);
                        frame.menuPage.setVisible(true);
                    }
                }
            }
        });

        backButton = new MyButton("Back",200,600);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.signUpPage.setVisible(false);
                frame.loginPage.setVisible(true);
            }
        });

        exitButton = new MyButton("Exit",300,600);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(nameLabel);
        add(surnameLabel);
        add(logLabel);
        add(pasLabel);
        add(repasLabel);
        add(nameField);
        add(surnameField);
        add(logField);
        add(pasField);
        add(repasField);
        add(checkBox);
        add(signUpButton);
        add(backButton);
        add(exitButton);
    }
}