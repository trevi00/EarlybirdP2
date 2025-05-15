package domain.user.service;

import domain.user.model.User;
import domain.user.repository.UserRepository;

public class DefaultUserService implements UserService {

    private final UserRepository repository;

    public DefaultUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User login(String username, String password) {
        User user = repository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    public boolean register(String username, String password, String displayName) {
        if (repository.existsByUsername(username)) {
            return false;
        }
        repository.save(new User(username, password, displayName));
        return true;
    }
}
