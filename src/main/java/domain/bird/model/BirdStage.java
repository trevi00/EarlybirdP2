package domain.bird.model;

public enum BirdStage {

    EGG("알", "세상에 나오려고 준비중입니다.", 10),
    BABY("아기 새", "조심스럽게 세상을 탐험합니다.", 30),
    CHILD("어린 새", "날개짓을 시작했습니다.", 60),
    ADULT("어른 새", "하늘 높이 비상할 준비가 되었습니다.", 100);

    private final String name;
    private final String description;
    private final int needPoint;

    BirdStage(String name, String description, int needPoint) {
        this.name = name;
        this.description = description;
        this.needPoint = needPoint;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getNeedPoint() { return needPoint; }

    public BirdStage getNextStage() {
        BirdStage[] stages = BirdStage.values();
        int idx = this.ordinal();
        return (idx + 1 < stages.length) ? stages[idx + 1] : null;
    }
}
