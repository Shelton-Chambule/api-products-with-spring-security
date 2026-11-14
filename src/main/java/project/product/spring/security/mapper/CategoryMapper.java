package project.product.spring.security.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import project.product.spring.security.dto.category.CategoryRequest;
import project.product.spring.security.dto.category.CategoryResponse;
import project.product.spring.security.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);

    Page<CategoryResponse> toResponse(Page<Category> list);

}
