package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;
import Data.User;
import Server.Server;

import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class TransferPage extends JPanel {
    private MyLabel toLabel, nameLabel, surnameLabel, nLabel, sLabel, sumLabel;
    private MyTextField toField, sumField;
    private MyButton okButton, historyButton, backButton, confirmButton;
    private Connection connection;
    private MainFrame frame = null;
    private String loginCurrent = new String("");
    private int moneyOfCurrent = 0;
    private PreparedStatement statement;

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
                nameLabel.setVisible(false);
                nLabel.setVisible(false);
                surnameLabel.setVisible(false);
                sLabel.setVisible(false);
                sumLabel.setVisible(false);
                sumField.setVisible(false);
                confirmButton.setVisible(false);
                toField.setText("");
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    loginCurrent = "";
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
                    statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
                    statement.setString(1, toField.getText());

                    ResultSet result = statement.executeQuery();

                    while(result.next())
                    {
                        nameLabel.setText("Name");
                        nameLabel.setVisible(true);
                        nLabel.setVisible(true);
                        surnameLabel.setVisible(true);
                        sLabel.setVisible(true);
                        sumLabel.setVisible(true);
                        sumField.setVisible(true);
                        confirmButton.setVisible(true);
                        nLabel.setText(result.getString("name"));
                        sLabel.setText(result.getString("surname"));

                    }
                    statement.close();
                }
                catch(Exception e){e.printStackTrace();
                }
                System.out.println(loginCurrent);
                System.out.println(toField.getText());
            }
        });

        nameLabel = new MyLabel("Name",100,250);
        nLabel = new MyLabel("",200,250);
        surnameLabel = new MyLabel("Surname",100,325);
        sLabel = new MyLabel("",200,325);
        sumLabel = new MyLabel("Sum",100,400);
        sumField = new MyTextField(200,400);
        confirmButton = new MyButton("Confirm",150,475);




        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC", "root", "");
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String check = new String(toField.getText());
                    if (loginCurrent.equalsIgnoreCase(String.valueOf(equals(check)))) {
                        nameLabel.setText("Нельзя перовадить самому себя");
                    } else {
                        statement = connection.prepareStatement("SELECT * FROM users WHERE login=?");
                        statement.setString(1, loginCurrent);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            moneyOfCurrent = resultSet.getInt("money");
                        }
                        statement.close();

                        if (moneyOfCurrent < Integer.parseInt(sumField.getText())) {
                            nameLabel.setText("Денги не хватает");
                            nLabel.setVisible(false);
                            surnameLabel.setVisible(false);
                            sLabel.setVisible(false);
                            sumLabel.setVisible(false);
                            sumField.setVisible(false);
                            confirmButton.setVisible(false);
                        } else {
                            statement = connection.prepareStatement("UPDATE users SET money=money-? WHERE login=?");
                            statement.setInt(1, Integer.parseInt(sumField.getText()));
                            statement.setString(2, loginCurrent);

                            statement.executeUpdate();
                            statement.close();

                            statement = connection.prepareStatement("UPDATE users SET money=money+? WHERE login=?");
                            statement.setInt(1, Integer.parseInt(sumField.getText()));
                            statement.setString(2, toField.getText());

                            statement.executeUpdate();
                            statement.close();

                            statement = connection.prepareStatement("INSERT INTO history (id, reciever, date, money) VALUES (null, ?, ?, ?)");
                            statement.setString(1, toField.getText());

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            LocalDate date = LocalDate.now();
                            String text = date.format(formatter);
                            LocalDate parsedDate = LocalDate.parse(text, formatter);

                            statement.setString(2, text);
                            statement.setInt(3, Integer.parseInt(sumField.getText()));

                            statement.executeUpdate();
                            statement.close();

                            nLabel.setVisible(false);
                            surnameLabel.setVisible(false);
                            sLabel.setVisible(false);
                            sumLabel.setVisible(false);
                            sumField.setVisible(false);
                            confirmButton.setVisible(false);
                            nameLabel.setText("Success");
                            loginCurrent = "";
                            sumField.setText("");
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        nameLabel.setVisible(false);
        nLabel.setVisible(false);
        surnameLabel.setVisible(false);
        sLabel.setVisible(false);
        sumLabel.setVisible(false);
        sumField.setVisible(false);
        confirmButton.setVisible(false);

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