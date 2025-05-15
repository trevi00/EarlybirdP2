// domain.bird.service.GrowthPolicy.java
package domain.bird.service;

import domain.bird.model.Bird;

public interface GrowthPolicy {
    boolean canEvolve(Bird bird);
    Bird evolve(Bird bird);
}
