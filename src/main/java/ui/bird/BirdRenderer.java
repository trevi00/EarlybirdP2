// ui.bird.BirdRenderer.java
package ui.bird;

import domain.bird.controller.BirdController;
import domain.bird.model.Bird;
import domain.bird.model.BirdStage;

import javax.swing.*;
import java.awt.*;

/**
 * 새 이미지 그리기
 */
public class BirdRenderer extends JPanel {

    private final BirdController controller;
    private final String userId;

    public BirdRenderer(BirdController controller, String userId) {
        this.controller = controller;
        this.userId = userId;
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Bird bird = controller.get(userId);
        BirdStage stage = bird.getStage();
        String path = "img/" + stage.name().toLowerCase() + ".png";
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(path));
        if (image != null) {
            g.drawImage(image, 30, 30, 128, 128, this);
        } else {
            g.drawString("이미지를 불러올 수 없습니다.", 50, 100);
        }
    }
}
