package domain.coupon.service;

import domain.coupon.model.CouponType;

public interface CouponService {

    boolean exchange(String userId, CouponType type);
}
