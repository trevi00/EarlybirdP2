package domain.point.model;

public class Point {
    private final String userId;
    private final int amount;

    public Point(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() { return userId; }
    public int getAmount() { return amount; }

    public Point withAdded(int value) {
        return new Point(userId, amount + value);
    }

    public Point withSubtracted(int value) {
        return new Point(userId, Math.max(0, amount - value));
    }
}
