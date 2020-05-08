package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyPasswordField;
import Client.Components.MyTextField;
import Data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {
    private MyLabel logLabel, pasLabel;
    private MyTextField logField;
    private MyPasswordField pasField;
    private JCheckBox checkBox;
    private MyButton logButton, signUpButton, exitButton;
    private MainFrame frame = null;

    public LoginPage(MainFrame frame){
        this.frame = frame;

        setSize(800, 800);
        setLayout(null);

        logLabel = new MyLabel("Login",100,100);
        logField = new MyTextField(200,100);
        pasLabel = new MyLabel("Password",100,200);
        pasField = new MyPasswordField(200,200);
        checkBox = new JCheckBox("Show password");
        checkBox.setBounds(400,200,150,30);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBox.isSelected()){
                    pasField.setEchoChar((char)0);
                }
                else{
                    pasField.setEchoChar('•');
                }
            }
        });



        logButton = new MyButton("Log in",100,300);
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logField.getText().isEmpty() || pasField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Fill all fields");
                }
                else{
                    User u = new User();
                    u.setLogin(logField.getText());
                    u.setPassword(pasField.getText());

                    User authUser = frame.clientSocket.toLogin(u);
                    if(authUser != null){
                        JOptionPane.showMessageDialog(frame, "You are Logged in");
                        logField.setText("");
                        pasField.setText("");

                        frame.loginPage.setVisible(false);
                        frame.menuPage.setVisible(true);

                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Incorrect login or password");
                }
            }
        });


        signUpButton = new MyButton("Sign up",200,300);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.loginPage.setVisible(false);
                frame.signUpPage.setVisible(true);
            }
        });


        exitButton = new MyButton("Exit",300,300);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(logLabel);
        add(logField);
        add(pasLabel);
        add(pasField);
        add(checkBox);
        add(logButton);
        add(signUpButton);
        add(exitButton);


    }
}
