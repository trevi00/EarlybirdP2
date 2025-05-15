package domain.coupon.model;

public enum CouponType {
    VITA500, COLA, COFFEE;

    public int getCost() {
        return switch (this) {
            case VITA500 -> 100;
            case COLA -> 300;
            case COFFEE -> 500;
        };
    }

    public String getDisplayName() {
        return switch (this) {
            case VITA500 -> "비타500";
            case COLA -> "콜라";
            case COFFEE -> "커피";
        };
    }
}
