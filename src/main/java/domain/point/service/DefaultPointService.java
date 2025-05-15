package domain.point.service;

import domain.point.repository.PointRepository;

public class DefaultPointService implements PointService {

    private final PointRepository repository;

    public DefaultPointService(PointRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addPoint(String userId, int amount) {
        repository.addPoint(userId, amount);
    }

    @Override
    public void subtractPoint(String userId, int amount) {
        repository.subtractPoint(userId, amount);
    }

    @Override
    public int getPoint(String userId) {
        return repository.getCurrentPoint(userId);
    }
}
