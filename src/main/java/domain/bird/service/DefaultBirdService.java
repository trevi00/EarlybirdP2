package domain.bird.service;

import domain.bird.model.Bird;

public class DefaultBirdService implements BirdService {

    @Override
    public Bird addPoint(Bird bird, int amount) {
        return bird.withAddedPoint(amount);
    }

    @Override
    public Bird evolve(Bird bird) {
        if (!bird.canEvolve()) return bird;
        return bird.evolveTo(bird.getStage().getNextStage());
    }
}
