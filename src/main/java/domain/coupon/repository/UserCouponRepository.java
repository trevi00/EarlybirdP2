package domain.coupon.repository;

import domain.coupon.model.Coupon;

import java.util.List;

public interface UserCouponRepository {
    List<Coupon> findAllByUserId(String userId);
}
