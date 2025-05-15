// domain.bird.model.Bird.java
package domain.bird.model;

import java.time.LocalDate;

public class Bird {
    private final String id;
    private final String userId;
    private final BirdStage stage;
    private final int point;
    private final LocalDate bornDate;

    public Bird(String id, String userId, BirdStage stage, int point, LocalDate bornDate) {
        this.id = id;
        this.userId = userId;
        this.stage = stage;
        this.point = point;
        this.bornDate = bornDate;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public BirdStage getStage() { return stage; }
    public int getPoint() { return point; }
    public LocalDate getBornDate() { return bornDate; }

    public Bird withAddedPoint(int amount) {
        return new Bird(id, userId, stage, point + amount, bornDate);
    }

    public Bird evolveTo(BirdStage nextStage) {
        return new Bird(id, userId, nextStage, 0, bornDate);
    }
}
