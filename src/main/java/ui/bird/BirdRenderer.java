package ui.bird;

import domain.bird.model.Bird;
import domain.bird.model.BirdStage;

import javax.swing.*;
import java.awt.*;

public class BirdRenderer extends JPanel {

    private final Bird bird;

    public BirdRenderer(Bird bird) {
        this.bird = bird;
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BirdStage stage = bird.getStage();
        String imagePath = "img/" + stage.name().toLowerCase() + ".png";
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(imagePath));

        if (img != null) {
            g.drawImage(img, 30, 30, 128, 128, this);
        } else {
            g.drawString("이미지를 불러올 수 없습니다.", 50, 100);
        }
    }
}
