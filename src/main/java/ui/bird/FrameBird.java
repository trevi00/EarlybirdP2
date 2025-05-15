package ui.bird;

import domain.bird.controller.BirdController;
import domain.bird.model.Bird;
import ui.component.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameBird extends JFrame {

    private final BirdController birdController;
    private final BirdMessageManager messageManager;

    private final BirdRenderer birdRenderer;
    private final JLabel lblInfo;

    public FrameBird(BirdController birdController, BirdMessageManager messageManager) {
        this.birdController = birdController;
        this.messageManager = messageManager;

        setTitle("새 보기");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Bird bird = birdController.getBird();

        // 그림 그리는 컴포넌트
        birdRenderer = new BirdRenderer(bird);
        birdRenderer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        birdRenderer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageManager.speakRandom();
            }
        });

        // 상태 표시
        lblInfo = new JLabel("", SwingConstants.CENTER);
        lblInfo.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        add(birdRenderer, BorderLayout.CENTER);
        add(lblInfo, BorderLayout.SOUTH);

        refresh();
        setVisible(true);
    }

    public void refresh() {
        Bird bird = birdController.getBird();
        lblInfo.setText("<html>🐤 현재 단계: " + bird.getStage().getName() + "<br>포인트: " + bird.getPoint() + "</html>");
        birdRenderer.repaint();
    }
}
