package domain.coupon.model;

import java.time.LocalDate;

public class Coupon {

    private final String id;
    private final String userId;
    private final CouponType type;
    private final LocalDate obtainedDate;

    public Coupon(String id, String userId, CouponType type, LocalDate obtainedDate) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.obtainedDate = obtainedDate;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public CouponType getType() { return type; }
    public LocalDate getObtainedDate() { return obtainedDate; }
}
