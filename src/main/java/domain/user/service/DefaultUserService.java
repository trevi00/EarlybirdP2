// domain.user.service.DefaultUserService.java
package domain.user.service;

import domain.user.model.User;
import domain.user.repository.UserRepository;

public class DefaultUserService implements UserService {

    private final UserRepository repo;

    public DefaultUserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User login(String username, String password) {
        User user = repo.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    public boolean register(String username, String password, String displayName) {
        if (repo.existsByUsername(username)) return false;
        repo.save(new User(username, password, displayName));
        return true;
    }

    @Override
    public boolean exists(String username) {
        return repo.existsByUsername(username);
    }
}
