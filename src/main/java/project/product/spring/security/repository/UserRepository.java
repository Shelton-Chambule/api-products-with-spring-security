package project.product.spring.security.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.product.spring.security.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);
    Optional<User> findByLogin(String login);
}
