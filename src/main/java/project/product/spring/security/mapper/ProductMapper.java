package project.product.spring.security.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import project.product.spring.security.dto.product.ProductRequest;
import project.product.spring.security.dto.product.ProductResponse;
import project.product.spring.security.entity.Product;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

    Page<ProductResponse> toResponseList(Page<Product> list);

    ProductResponse toResponseRemove(Optional<Product> product);

}
