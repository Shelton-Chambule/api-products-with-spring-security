package project.product.spring.security.services;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import project.product.spring.security.dto.product.ProductRequest;
import project.product.spring.security.dto.product.ProductResponse;
import project.product.spring.security.entities.Category;
import project.product.spring.security.entities.Product;
import project.product.spring.security.repository.CategoryRepository;
import project.product.spring.security.repository.ProductRepository;
import project.product.spring.security.services.exeption.ResourceNotFound;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServices {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServices(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse saveProduct(ProductRequest productRequestDTO) {

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFound(productRequestDTO.getCategoryId()));

        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return new ProductResponse(product);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFound(id);
        }
        productRepository.deleteById(id);

    }

    public ProductResponse update(Long id, ProductRequest productRequestDTO) {
        try {
            Product product = productRepository.getReferenceById(id);
            updateProduct(product, productRequestDTO);
            return new ProductResponse(product);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFound(id);
        }
    }

    private void updateProduct(Product product, ProductRequest productRequestDTO) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFound(productRequestDTO.getCategoryId()));
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(category);
    }

    public List<ProductResponse> findAll(){
       List<Product> products = productRepository.findAll();
       return products.stream().map(ProductResponse::new).collect(Collectors.toList());
    }
}
