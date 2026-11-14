package project.product.spring.security.dto.product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entity.Product;

@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private Long categoryId;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryId = product.getCategory().getId();
    }
}
