package domain.coupon.service;

import domain.coupon.model.Coupon;
import domain.coupon.model.CouponType;
import domain.coupon.repository.CouponRepository;
import domain.point.repository.PointRepository;

import java.time.LocalDate;
import java.util.UUID;

public class DefaultCouponService implements CouponService {

    private final CouponRepository couponRepository;
    private final PointRepository pointRepository;

    public DefaultCouponService(CouponRepository couponRepository, PointRepository pointRepository) {
        this.couponRepository = couponRepository;
        this.pointRepository = pointRepository;
    }

    @Override
    public boolean exchange(String userId, CouponType type) {
        int currentPoint = pointRepository.getCurrentPoint(userId);
        int cost = type.getCost();

        if (currentPoint < cost) return false;

        pointRepository.subtractPoint(userId, cost);

        Coupon coupon = new Coupon(
                UUID.randomUUID().toString(),
                userId,
                type,
                LocalDate.now()
        );
        couponRepository.save(coupon);
        return true;
    }
}
