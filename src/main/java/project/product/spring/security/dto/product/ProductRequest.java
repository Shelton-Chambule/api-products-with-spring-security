package project.product.spring.security.dto.product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entities.Product;

@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {

    @NotBlank(message = "Required filed")
    private String name;

    @NotNull(message = "Required filed")
    private Double price;

    @NotNull(message = "Required filed")
    private Long categoryId;

    public ProductRequest(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryId = product.getCategory().getId();
    }
}
