package project.product.spring.security.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.product.spring.security.entities.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

}
