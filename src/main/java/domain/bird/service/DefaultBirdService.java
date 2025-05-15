// domain.bird.service.DefaultBirdService.java
package domain.bird.service;

import domain.bird.model.Bird;
import domain.bird.repository.BirdRepository;

public class DefaultBirdService implements BirdService {

    private final BirdRepository repo;
    private final GrowthPolicy growthPolicy;

    public DefaultBirdService(BirdRepository repo, GrowthPolicy growthPolicy) {
        this.repo = repo;
        this.growthPolicy = growthPolicy;
    }

    @Override
    public Bird get(String userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public Bird addPoint(String userId, int amount) {
        Bird bird = repo.findByUserId(userId);
        Bird updated = bird.withAddedPoint(amount);
        repo.save(updated);
        return updated;
    }

    @Override
    public Bird evolveIfNeeded(String userId) {
        Bird bird = repo.findByUserId(userId);
        if (growthPolicy.canEvolve(bird)) {
            Bird evolved = growthPolicy.evolve(bird);
            repo.save(evolved);
            return evolved;
        }
        return bird;
    }
}
