// ui.main.FrameMain.java
package ui.main;

import app.context.EarlyBirdContext;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * EarlyBird 메인 프레임 (컨트롤러 기반)
 */
public class FrameMain extends JFrame {

    private final EarlyBirdContext context;

    public FrameMain() {
        setGlobalUIStyle();

        setTitle("EarlyBird 🌅");
        setSize(500, 600);
        setMinimumSize(new Dimension(500, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.context = new EarlyBirdContext();

        // ✅ Controller 기반 메뉴 패널 전달
        MainMenuPanel mainPanel = new MainMenuPanel(this, context);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        setContentPane(scrollPane);

        setVisible(true);
    }

    private void setGlobalUIStyle() {
        Font font = new Font("맑은 고딕", Font.PLAIN, 14);
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }

        UIManager.put("Button.background", new Color(135, 206, 250));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Label.foreground", Color.DARK_GRAY);
    }

    public static void main(String[] args) {
        new FrameMain();
    }
}
