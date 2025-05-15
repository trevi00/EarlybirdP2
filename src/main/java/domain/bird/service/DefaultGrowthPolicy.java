// domain.bird.service.DefaultGrowthPolicy.java
package domain.bird.service;

import domain.bird.model.Bird;
import domain.bird.model.BirdStage;

public class DefaultGrowthPolicy implements GrowthPolicy {

    @Override
    public boolean canEvolve(Bird bird) {
        BirdStage next = bird.getStage().next();
        return next != null && bird.getPoint() >= bird.getStage().getRequiredPoint();
    }

    @Override
    public Bird evolve(Bird bird) {
        if (!canEvolve(bird)) return bird;
        return bird.evolveTo(bird.getStage().next());
    }
}
