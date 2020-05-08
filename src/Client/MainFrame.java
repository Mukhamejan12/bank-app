package Client;

import Client.Network.ClientSocket;

import javax.swing.*;

public class MainFrame extends JFrame {
    public SignUpPage signUpPage;
    public LoginPage loginPage;
    public ClientSocket clientSocket;
    public MenuPage menuPage;
    public ProfilePage profilePage;
    public DepositPage depositPage;
    public CreditPage creditPage;
    public FormalizeCreditPage formalizeCreditPage;
    public OpenDepositPage openDepositPage;
    public TransferPage transferPage;
    public HistoryPage historyPage;
    public ReplenishPage replenishPage;

    public MainFrame(){
        clientSocket = new ClientSocket();

        setSize(600,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        loginPage = new LoginPage(this);
        loginPage.setVisible(true);
        add(loginPage);

        signUpPage = new SignUpPage(this);
        signUpPage.setVisible(false);
        add(signUpPage);

        menuPage = new MenuPage(this);
        menuPage.setVisible(false);
        add(menuPage);

        profilePage = new ProfilePage(this);
        profilePage.setVisible(false);
        add(profilePage);

        transferPage = new TransferPage(this);
        transferPage.setVisible(false);
        add(transferPage);

        depositPage = new DepositPage(this);
        depositPage.setVisible(false);
        add(depositPage);

        creditPage = new CreditPage(this);
        creditPage.setVisible(false);
        add(creditPage);

        formalizeCreditPage = new FormalizeCreditPage(this);
        formalizeCreditPage.setVisible(false);
        add(formalizeCreditPage);

        openDepositPage = new OpenDepositPage(this);
        openDepositPage.setVisible(false);
        add(openDepositPage);

        historyPage = new HistoryPage(this);
        historyPage.setVisible(false);
        add(historyPage);

        replenishPage = new ReplenishPage(this);
        replenishPage.setVisible(false);
        add(replenishPage);
    }
}
