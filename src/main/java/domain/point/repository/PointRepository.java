package domain.point.repository;

public interface PointRepository {

    int getCurrentPoint(String userId);

    void addPoint(String userId, int amount);

    void subtractPoint(String userId, int amount);
}
