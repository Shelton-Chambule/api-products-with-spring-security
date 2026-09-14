package project.product.spring.security.dto.category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entities.Category;

@NoArgsConstructor
@Setter
@Getter
public class CategoryRequest {

    @NotBlank(message = "Required filed")
    private String name;

    public CategoryRequest(Category category) {
        this.name = category.getName();
    }
}
