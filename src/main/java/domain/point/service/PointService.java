package domain.point.service;

public interface PointService {

    void addPoint(String userId, int amount);

    void subtractPoint(String userId, int amount);

    int getPoint(String userId);
}
