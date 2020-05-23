package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;
import Data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormalizeCreditPage extends JPanel {
    private MyLabel sumLabel, monthLabel, jobLabel, incomeLabel, numberLabel;
    private MyTextField sumField, incomeField, numberField;
    private JComboBox monthBox;
    private JCheckBox jobBox;
    private MyButton okButton, backButton;
    private String month[] = {"", "6 month", "12 month", "36 month"};
    private MainFrame frame = null;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String loginCurrent = "";

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

        User user = new User();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }

        okButton = new MyButton("OK",300,475);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try(FileReader reader = new FileReader("currentUser.txt")) {
                    int c;
                    while((c=reader.read())!=-1){
                        loginCurrent += String.valueOf((char)c);
                    }
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                if(Integer.parseInt(incomeField.getText())/Integer.parseInt(numberField.getText()) > 42500) {
                    if (!jobBox.isSelected()) {
                        JOptionPane.showMessageDialog(frame, "Credit is rejected because you don't have a job");
                    } else if (sumField.getText().isEmpty() || incomeField.getText().isEmpty() || numberField.getText().isEmpty() || monthBox.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(frame, "Fill all fields");
                    } else {


                        try {
                            preparedStatement = connection.prepareStatement("INSERT INTO credit (id, login, money, money_month, percent, month) VALUES (null, ?, ?, ?, ?, ?)");
                            preparedStatement.setString(1, loginCurrent);
                            preparedStatement.setInt(2, Integer.parseInt(sumField.getText()) * 28 / 25);
                            //money_month
                            if (monthBox.getSelectedIndex() == 0) {
                            }
                            if (monthBox.getSelectedIndex() == 1) {
                                preparedStatement.setInt(3, Integer.parseInt(sumField.getText()) * 14 / 75);
                                preparedStatement.setInt(5, 6);
                            } else if (monthBox.getSelectedIndex() == 2) {
                                preparedStatement.setInt(3, Integer.parseInt(sumField.getText()) * 7 / 75);
                                preparedStatement.setInt(5, 12);
                            } else if (monthBox.getSelectedIndex() == 3) {
                                preparedStatement.setInt(3, Integer.parseInt(sumField.getText()) * 7 / 225);
                                preparedStatement.setInt(5, 36);
                            }

                            preparedStatement.setInt(4, 12);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            PreparedStatement statement = connection.prepareStatement("UPDATE users SET credit=?, money=money+? WHERE login=?");
                            statement.setInt(1, Integer.parseInt(sumField.getText()));
                            statement.setInt(2, Integer.parseInt(sumField.getText()));

                            statement.setString(3, loginCurrent);
                            statement.executeUpdate();
                            statement.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(frame, "Success");

                        sumField.setText("");
                        monthBox.setSelectedIndex(0);
                        incomeField.setText("");
                        numberField.setText("");

                    }
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