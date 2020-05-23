package Client;

import Client.Components.MyButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DepositPage extends JPanel {
    private MyButton openButton, backButton, refreshButton;
    private MainFrame frame = null;
    private Connection connection;
    private String loginCurrent = "";
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String[] income = new String[100];
    private String[] income_month = new String[100];
    private String[] percent = new String[100];
    private String[] number_month = new String[100];
    private int i = 0;

    public DepositPage(MainFrame frame) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC","root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        JTextArea jTextArea = new JTextArea();
        jTextArea.setBounds(50, 150, 500, 400);

        openButton = new MyButton("Open a deposit", 100, 100);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.depositPage.setVisible(false);
                frame.openDepositPage.setVisible(true);
                jTextArea.setText("");
            }
        });

        backButton = new MyButton("Back", 200, 100);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.depositPage.setVisible(false);
                frame.menuPage.setVisible(true);
                jTextArea.setText("");
            }
        });




        refreshButton = new MyButton("Refresh",300,100);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    try(FileReader reader = new FileReader("currentUser.txt"))
                    {
                        int c;
                        while((c=reader.read())!=-1){
                            loginCurrent += String.valueOf((char)c);
                        }
                    } catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }

                    preparedStatement = connection.prepareStatement("SELECT * FROM deposit WHERE login=?");
                    preparedStatement.setString(1, loginCurrent);
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next())
                    {

                        income[i] = resultSet.getString("income");
                        income_month[i] = resultSet.getString("income_month");
                        percent[i] = resultSet.getString("percent");
                        number_month[i] = resultSet.getString("number_month");
                        i++;
                    }



                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        resultSet.close();
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                jTextArea.setText("Income\tIncome month\tPercent\tNumber month\n");
                int k = 0;
                while(i > k) {
                    jTextArea.setText(jTextArea.getText() + income[k] + "\t" + income_month[k] + "\t" + percent[k] + "\t" + number_month[k] + "\n");
                    k++;
                }

            }
        });
        add(jTextArea);
        add(openButton);
        add(backButton);
        add(refreshButton);
    }
}