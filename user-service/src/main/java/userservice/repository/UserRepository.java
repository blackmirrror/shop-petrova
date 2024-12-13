package userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import userservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    String getGeneratedIdByLogin(String login);
}
