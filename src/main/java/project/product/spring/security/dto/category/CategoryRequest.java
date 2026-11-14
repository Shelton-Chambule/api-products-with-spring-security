package project.product.spring.security.dto.category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entity.Category;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "Required filed")
    private String name;

}
