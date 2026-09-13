package project.product.spring.security.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_product")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

    private Long id;
    private String name;
    private Double price;

}
