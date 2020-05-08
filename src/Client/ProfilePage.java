package Client;

import Client.Components.MyButton;
import Client.Components.MyLabel;
import Client.Components.MyTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfilePage extends JPanel {
    private MyLabel moneyLabel, mLabel, nameLabel, snameLabel, nLabel, sLabel, creditLabel, cLabel, deppositLabel, dLabel, yourlogin;
    private MyButton backButton, refreshButton;
    private MyTextField loginField;
    public MainFrame frame = null;
    private Connection connection;

    public ProfilePage(MainFrame frame){
        this.frame = frame;
        setSize(500,800);
        setLayout(null);

        yourlogin = new MyLabel("Your login",100,50);
        loginField = new MyTextField(200,50);

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

                loginField.setText("");
                moneyLabel.setText("");
                nameLabel.setText("");
                snameLabel.setText("");
                creditLabel.setText("");
                deppositLabel.setText("");
            }
        });

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
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
                    statement.setString(1, loginField.getText());

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
        add(yourlogin);
        add(loginField);
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
