// ui.message.BirdMessageManager.java
package ui.message;

import bird.message.BirdMessageDisplayer;
import bird.message.BirdMessageProvider;
import domain.bird.model.Bird;
import domain.bird.controller.BirdController;

public class BirdMessageManager {

    private final BirdController controller;
    private final BirdMessageProvider provider;
    private final BirdMessageDisplayer displayer;

    public BirdMessageManager(BirdController controller,
                              BirdMessageProvider provider,
                              BirdMessageDisplayer displayer) {
        this.controller = controller;
        this.provider = provider;
        this.displayer = displayer;
    }

    /**
     * 새의 현재 상태에 따른 응원 메시지를 즉시 팝업
     */
    public void speakRandom(String userId) {
        Bird bird = controller.get(userId);
        String message = provider.getMessage(bird.getStage());
        displayer.speak(message);
    }

    /**
     * 상단 배너로 메시지 출력
     */
    public void say(String message) {
        displayer.showBanner(message);
    }
}
