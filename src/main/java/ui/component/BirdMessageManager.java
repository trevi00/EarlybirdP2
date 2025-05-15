package ui.component;

import domain.bird.model.Bird;
import domain.bird.model.BirdStage;
import domain.bird.service.BirdMessageProvider;

import javax.swing.*;

/**
 * [BirdMessageManager]
 * - 새 메시지를 UI로 전달하는 중간 관리자
 * - 팝업(speak) 또는 배너(say) 형식으로 출력
 */
public class BirdMessageManager {

    private final Bird bird;
    private final BirdMessageProvider messageProvider;
    private final JFrame parentFrame;
    private final ImageIcon birdIcon;

    public BirdMessageManager(Bird bird, BirdMessageProvider messageProvider, JFrame parentFrame, ImageIcon birdIcon) {
        this.bird = bird;
        this.messageProvider = messageProvider;
        this.parentFrame = parentFrame;
        this.birdIcon = birdIcon;
    }

    /**
     * 새의 현재 상태에 맞는 메시지를 팝업으로 출력
     */
    public void speakRandom() {
        BirdStage stage = bird.getStage();
        String message = messageProvider.getMessage(stage);
        JOptionPane.showMessageDialog(parentFrame, "🐤 " + message);
    }

    /**
     * 상단 배너로 메시지 출력
     */
    public void say(String message) {
        Container content = parentFrame.getContentPane();

        if (!(content.getLayout() instanceof BorderLayout)) {
            content.setLayout(new BorderLayout());
        }

        BirdBanner banner = new BirdBanner(birdIcon, message);
        content.add(banner, BorderLayout.NORTH);
        content.revalidate();
        content.repaint();
    }
}
