package Client.Components;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String text, int x, int y){
        setText(text);
        setForeground(Color.BLACK);
        setSize(100,30);
        setLocation(x, y);
        //setFont(new Font("Arial",Font.BOLD,16));
    }
}
