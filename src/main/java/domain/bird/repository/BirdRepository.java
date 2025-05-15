// domain.bird.repository.BirdRepository.java
package domain.bird.repository;

import domain.bird.model.Bird;

public interface BirdRepository {
    Bird findByUserId(String userId);
    void save(Bird bird);
}
