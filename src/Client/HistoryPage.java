package Client;

import Client.Components.MyButton;

import javax.swing.*;

public class HistoryPage extends JPanel {
    private MyButton historyButton;
    private MainFrame frame = null;

    public HistoryPage(MainFrame frame) {
        setSize(800, 600);
        setLayout(null);
        this.frame = frame;

        historyButton = new MyButton("Button",100,100);
        add(historyButton);
    }
}
