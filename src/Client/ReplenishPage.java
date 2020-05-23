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

public class ReplenishPage extends JPanel {
    private MyLabel sumLabel;
    private MyTextField sumField;
    private MyLabel loginLabel;
    private MyTextField loginField;
    private MyButton replenishButton, backButton;
    private MainFrame frame = null;
    private Connection connection;
    private String loginCurrent = "";

    public ReplenishPage (MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        sumLabel = new MyLabel("Sum",100,150);
        sumField = new MyTextField(250,150);


        replenishButton = new MyButton("Replenish",250,250);
        replenishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&serverTimezone=UTC","root", "");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try{

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

                    PreparedStatement statement = connection.prepareStatement("UPDATE users SET money=money+? WHERE login=?");
                    statement.setInt(1, Integer.parseInt(sumField.getText()));
                    statement.setString(2, loginCurrent);

                    statement.executeUpdate();
                    statement.close();
                }
                catch(Exception e){e.printStackTrace();}

                sumField.setText("");

            }
        });

        backButton = new MyButton("Back",150,250);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.replenishPage.setVisible(false);
                frame.menuPage.setVisible(true);
            }
        });
        add(backButton);
        add(sumLabel);
        add(sumField);
        add(replenishButton);
    }
}
