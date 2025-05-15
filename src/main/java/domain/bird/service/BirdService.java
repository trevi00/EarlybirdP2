// domain.bird.service.BirdService.java
package domain.bird.service;

import domain.bird.model.Bird;

public interface BirdService {
    Bird get(String userId);
    Bird addPoint(String userId, int amount);
    Bird evolveIfNeeded(String userId);
}
