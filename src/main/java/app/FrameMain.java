// app/FrameMain.java
package app;

import app.context.EarlyBirdContext;
import app.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class FrameMain extends JFrame {

    public FrameMain(EarlyBirdContext context) {
        setTitle("EarlyBird 🌅");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenuPanel panel = new MainMenuPanel(context);
        setContentPane(new JScrollPane(panel));

        setVisible(true);
    }
}
