package project.product.spring.security.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_category")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category {

    private Long id;
    private String name;

}
