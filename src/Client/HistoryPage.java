package Client;

import Client.Components.MyButton;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HistoryPage extends JPanel {
    private MyButton backButton;
    private MainFrame frame = null;
    private JPanel contents;
    private Connection connection;
    private String aqr = "";

    public HistoryPage(MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        backButton = new MyButton("Back",50,50);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.historyPage.setVisible(false);
                frame.transferPage.setVisible(true);
            }
        });

        JTextArea jTextArea = new JTextArea();
        jTextArea.setBounds(50, 100, 500, 400);

        add(jTextArea);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC","root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM history");
            jTextArea.setText("Reciever\tDate\tMoney\n");
            while (resultSet.next())
            {
                String reciever = resultSet.getString("reciever");
                String date = resultSet.getString("date");
                String money = resultSet.getString("money");

                jTextArea.setText(jTextArea.getText() + reciever + "\t" + date + "\t" + money + "\r\n");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}

