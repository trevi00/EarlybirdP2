package domain.bird.service;

import domain.bird.model.Bird;

public interface BirdService {

    Bird addPoint(Bird bird, int amount);

    Bird evolve(Bird bird);
}
