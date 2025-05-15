package domain.bird.repository;

import domain.bird.model.Bird;

public interface BirdRepository {

    void save(Bird bird);

    Bird findByUsername(String userId);
}
