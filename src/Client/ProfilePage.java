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
import java.sql.*;

public class ProfilePage extends JPanel {
    private MyLabel moneyLabel, mLabel, nameLabel, snameLabel, nLabel, sLabel, creditLabel, cLabel, deppositLabel, dLabel, yourlogin;
    private MyButton backButton, refreshButton;
    private MyTextField loginField;
    public MainFrame frame = null;
    private Connection connection;
    private String loginCurrent = "";
    private JTextArea jTextArea;

    public ProfilePage(MainFrame frame){
        this.frame = frame;
        setSize(500,800);
        setLayout(null);

        mLabel = new MyLabel("Sum",100,100);
        moneyLabel = new MyLabel("", 200,100);

        nLabel = new MyLabel("Name", 100,200);
        nameLabel = new MyLabel("",200,200);

        sLabel = new MyLabel("Surname",100,300);
        snameLabel = new MyLabel("",200,300);

        cLabel = new MyLabel("Credit",100,400);
        creditLabel = new MyLabel("",200,400);

        dLabel = new MyLabel("Deposit",100,500);
        deppositLabel = new MyLabel("",200,500);

        backButton = new MyButton("Back",150,600);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.profilePage.setVisible(false);
                frame.menuPage.setVisible(true);

                moneyLabel.setText("");
                nameLabel.setText("");
                snameLabel.setText("");
                creditLabel.setText("");
                deppositLabel.setText("");
                loginCurrent = "";
            }
        });

        jTextArea = new JTextArea();
        jTextArea.setBounds(50, 150, 500, 400);

        refreshButton = new MyButton("Refresh", 250, 600);
        refreshButton.addActionListener(new ActionListener() {
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

                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
                    statement.setString(1, loginCurrent);
                    ResultSet result = statement.executeQuery();

                    while(result.next())
                    {
                        moneyLabel.setText(String.valueOf(result.getInt("money")));
                        nameLabel.setText(result.getString("name"));
                        snameLabel.setText(result.getString("surname"));
                        creditLabel.setText(result.getString("credit"));
                        deppositLabel.setText(result.getString("deposit"));

                    }

                    statement.close();
                }
                catch(Exception e){e.printStackTrace();
                }
            }
        });

        add(refreshButton);
        add(mLabel);
        add(moneyLabel);
        add(nLabel);
        add(nameLabel);
        add(sLabel);
        add(snameLabel);
        add(cLabel);
        add(creditLabel);
        add(dLabel);
        add(deppositLabel);
        add(backButton);
    }
}
