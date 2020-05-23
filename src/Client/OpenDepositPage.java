package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OpenDepositPage extends JPanel{
    private JComboBox monthBox;
    private MyLabel monthLabel, sumLabel, percentLabel, errorLabel;
    private MyTextField sumField;
    private MyButton backButton, okButton;
    private String month[] = {"Number of month", "6 month", "12 month", "36 month"};
    private MainFrame frame = null;
    private Connection connection;
    private int moneyOfCurrent = 0;
    private String loginCurrent = "";
    private PreparedStatement statement;

    public OpenDepositPage(MainFrame frame) {
        this.frame = frame;
        setSize(800,600);
        setLayout(null);

        errorLabel = new MyLabel("asdad", 100, 650);
        add(errorLabel);
        sumLabel = new MyLabel("Total money", 100,200);
        sumField = new MyTextField(300,200);
        monthLabel = new MyLabel("Number of month",100,350);
        monthBox = new JComboBox(month);
        monthBox.setBounds(300,350,100,30);
        percentLabel = new MyLabel("12%",200,450);
        backButton = new MyButton("Back",100,550);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.openDepositPage.setVisible(false);
                frame.depositPage.setVisible(true);
            }
        });
        okButton = new MyButton("OK",300,550);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC","root", "");
                    try(FileReader reader = new FileReader("currentUser.txt"))
                    {
                        int c;
                        while((c=reader.read())!=-1){
                            loginCurrent += String.valueOf((char)c);
                        }
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try{

                    statement = connection.prepareStatement("SELECT * FROM users WHERE login=?");
                    statement.setString(1, loginCurrent);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        moneyOfCurrent = resultSet.getInt("money");
                    }
                    statement.close();


                    if (moneyOfCurrent < Integer.parseInt(sumField.getText())) {
                        percentLabel.setText("Денги не хватает");
                    } else {
                        percentLabel.setText("12%");
                        statement = connection.prepareStatement("UPDATE users SET money=money-? WHERE login=?");
                        statement.setInt(1, Integer.parseInt(sumField.getText()));
                        statement.setString(2, loginCurrent);
                        statement.executeUpdate();
                        statement.close();

                        System.out.println(loginCurrent);
                        System.out.println(sumField.getText());

                        statement = connection.prepareStatement("UPDATE users SET deposit=deposit+? WHERE login=?");
                        statement.setInt(1, Integer.parseInt(sumField.getText()));
                        statement.setString(2, loginCurrent);
                        statement.executeUpdate();
                        statement.close();

                        PreparedStatement statement = connection.prepareStatement("INSERT INTO deposit (id, login, income, income_month, percent, number_month) VALUES (null, ?, ?, ?, ?, ?)");
                        statement.setString(1, loginCurrent);
                        statement.setInt(2, Integer.parseInt(sumField.getText()) * 28/25);
                        if (monthBox.getSelectedIndex() == 0) {
                        }
                        if (monthBox.getSelectedIndex() == 1) {
                            statement.setInt(3, Integer.parseInt(sumField.getText()) * 14 / 75);
                        } else if (monthBox.getSelectedIndex() == 2) {
                            statement.setInt(3, Integer.parseInt(sumField.getText()) * 7 / 75);
                        } else if (monthBox.getSelectedIndex() == 3) {
                            statement.setInt(3, Integer.parseInt(sumField.getText()) * 7 / 225);
                        }
                        statement.setInt(4, 12);
                        if (monthBox.getSelectedIndex() == 0) {
                        }
                        if (monthBox.getSelectedIndex() == 1) {
                            statement.setInt(5, 6);
                        } else if (monthBox.getSelectedIndex() == 2) {
                            statement.setInt(5, 12);
                        } else if (monthBox.getSelectedIndex() == 3) {
                            statement.setInt(5, 36);
                        }
                        statement.executeUpdate();
                        statement.close();

                        JOptionPane.showMessageDialog(frame,"Success");

                        sumField.setText("");
                        monthBox.setSelectedIndex(0);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }



            }
        });


        add(sumLabel);
        add(sumField);
        add(monthLabel);
        add(monthBox);
        add(percentLabel);
        add(backButton);
        add(okButton);
    }
}