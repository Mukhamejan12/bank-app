package Client;

import Client.Components.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class CreditPage extends JPanel {
    private MyButton formalizeButton, backButton, refreshButton;
    private MainFrame frame = null;
    private Connection connection;
    private String loginCurrent = "";
    private JTextArea jTextArea;

    public CreditPage(MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;


        formalizeButton = new MyButton("Formalize credit", 100,100);
        formalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.creditPage.setVisible(false);
                frame.formalizeCreditPage.setVisible(true);
            }
        });


        backButton = new MyButton("Back",200,100);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.creditPage.setVisible(false);
                frame.menuPage.setVisible(true);
            }
        });

        jTextArea = new JTextArea();
        jTextArea.setBounds(50, 150, 500, 400);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC","root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }


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
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }

                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit WHERE login=?");
                    statement.setString(1, loginCurrent);
                    System.out.println(loginCurrent);
                    ResultSet resultSet = statement.executeQuery();
                    jTextArea.setText("Money\tMoney month\tpercent\tMonth\n");
                    while (resultSet.next())
                    {
                        String money = resultSet.getString("money");
                        String money_month = resultSet.getString("money_month");
                        String percent = resultSet.getString("percent");
                        String month = resultSet.getString("month");
                        jTextArea.setText(jTextArea.getText() + money + "\t" + money_month + "\t" + percent + "\t" + month + "\r\n");
                    }

                    resultSet.close();
                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        add(jTextArea);
        add(formalizeButton);
        add(backButton);
        add(refreshButton);
    }
}

