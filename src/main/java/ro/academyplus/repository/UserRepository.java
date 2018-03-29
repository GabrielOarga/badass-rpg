package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.data.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
}
