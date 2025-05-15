// domain.bird.model.BirdStage.java
package domain.bird.model;

public enum BirdStage {
    EGG("알", 10),
    BABY("아기 새", 30),
    CHILD("어린 새", 60),
    ADULT("어른 새", 100);

    private final String name;
    private final int requiredPoint;

    BirdStage(String name, int requiredPoint) {
        this.name = name;
        this.requiredPoint = requiredPoint;
    }

    public String getName() { return name; }
    public int getRequiredPoint() { return requiredPoint; }

    public BirdStage next() {
        return switch (this) {
            case EGG -> BABY;
            case BABY -> CHILD;
            case CHILD -> ADULT;
            case ADULT -> null;
        };
    }
}
