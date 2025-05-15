// ui.bird.FrameBird.java
package ui.bird;

import domain.bird.controller.BirdController;
import domain.bird.model.Bird;
import ui.message.BirdMessageManager;
import user.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 새 상태 UI
 */
public class FrameBird extends JFrame {

    private final BirdController controller;
    private final BirdMessageManager messageManager;

    private final BirdRenderer renderer;
    private final JLabel lblInfo;
    private final String userId;

    public FrameBird(BirdController controller, BirdMessageManager messageManager) {
        this.controller = controller;
        this.messageManager = messageManager;
        this.userId = SessionManager.getCurrentUser().getId();

        setTitle("새 보기");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        renderer = new BirdRenderer(controller, userId);
        renderer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        renderer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageManager.speakRandom(userId);
            }
        });

        lblInfo = new JLabel("", SwingConstants.CENTER);
        lblInfo.setFont(new Font("Serif", Font.BOLD, 16));

        add(renderer, BorderLayout.CENTER);
        add(lblInfo, BorderLayout.SOUTH);

        refresh();
        setVisible(true);
    }

    public void refresh() {
        Bird bird = controller.get(userId);
        lblInfo.setText("<html>단계: " + bird.getStage().getName() +
                "<br>포인트: " + bird.getPoint() + "</html>");
        renderer.repaint();
    }
}
