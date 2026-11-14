package project.product.spring.security.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.product.spring.security.entity.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

}
