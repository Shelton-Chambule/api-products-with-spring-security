package project.product.spring.security.dto.category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entities.Category;

@NoArgsConstructor
@Setter
@Getter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

}
