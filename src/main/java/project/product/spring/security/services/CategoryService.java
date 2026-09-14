package project.product.spring.security.services;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import project.product.spring.security.dto.category.CategoryRequest;
import project.product.spring.security.dto.category.CategoryResponse;
import project.product.spring.security.entities.Category;
import project.product.spring.security.repository.CategoryRepository;
import project.product.spring.security.services.exeption.ResourceNotFound;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return new CategoryResponse(category);
    }

    public List<CategoryResponse> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return  categories.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    public CategoryResponse findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return new CategoryResponse(category.orElseThrow(() -> new ResourceNotFound(id)));
    }

    public  CategoryResponse update(Long id, CategoryRequest categoryRequest){
        try{
            Category categoryId = categoryRepository.getReferenceById(id);
            updateCategory(categoryId, categoryRequest);
            categoryRepository.save(categoryId);
            return new CategoryResponse(categoryId);
        }catch (EntityNotFoundException exception){
            throw new ResourceNotFound(id);
        }
    }

    private void updateCategory(Category categoryId, CategoryRequest categoryRequest) {
            categoryId.setName(categoryId.getName());
    }
}
