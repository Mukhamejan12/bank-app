package Client.Components;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String text, int x, int y){
        setText(text);
        setForeground(Color.BLACK);
        setBackground(Color.cyan);
        setSize(100,30);
        setLocation(x, y);
        //setFont(new Font("Arial",Font.BOLD,16));
    }

}
