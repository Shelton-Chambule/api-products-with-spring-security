package project.product.spring.security.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.product.spring.security.dto.category.CategoryRequest;
import project.product.spring.security.dto.category.CategoryResponse;
import project.product.spring.security.services.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        CategoryResponse product = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/find/all")
    public  ResponseEntity<List<CategoryResponse>> findAll(){
        List<CategoryResponse> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryResponse> findById(@PathVariable Long categoryId){
        CategoryResponse category = categoryService.findById(categoryId);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{categoryId}")
    public  ResponseEntity<CategoryResponse> update(@PathVariable  Long categoryId, @RequestBody @Valid CategoryRequest categoryRequest){
        CategoryResponse category = categoryService.update(categoryId,categoryRequest);
        return ResponseEntity.ok().body(category);
    }
}
