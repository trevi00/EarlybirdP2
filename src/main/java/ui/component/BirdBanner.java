package ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * [BirdBanner]
 * - 새의 메시지를 상단에 표시하는 배너 컴포넌트
 */
public class BirdBanner extends JPanel {

    public BirdBanner(ImageIcon birdIcon, String message) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        setBackground(new Color(255, 250, 205)); // 연노랑
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel iconLabel = new JLabel();
        if (birdIcon != null) {
            iconLabel.setIcon(resizeIcon(birdIcon, 32, 32));
        }

        JLabel messageLabel = new JLabel("🐤 " + message);
        messageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        messageLabel.setForeground(Color.DARK_GRAY);

        add(iconLabel);
        add(messageLabel);
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
