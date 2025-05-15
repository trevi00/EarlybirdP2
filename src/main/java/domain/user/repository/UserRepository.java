// domain.user.repository.UserRepository.java
package domain.user.repository;

import domain.user.model.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
    boolean existsByUsername(String username);
}
