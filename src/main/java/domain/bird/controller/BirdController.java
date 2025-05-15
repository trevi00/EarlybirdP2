// domain.bird.controller.BirdController.java
package domain.bird.controller;

import domain.bird.model.Bird;
import domain.bird.service.BirdService;

public class BirdController {

    private final BirdService service;

    public BirdController(BirdService service) {
        this.service = service;
    }

    public Bird get(String userId) {
        return service.get(userId);
    }

    public Bird addPoint(String userId, int amount) {
        return service.addPoint(userId, amount);
    }

    public Bird evolveIfPossible(String userId) {
        return service.evolveIfNeeded(userId);
    }
}
