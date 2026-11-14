package project.product.spring.security.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.product.spring.security.dto.category.CategoryRequest;
import project.product.spring.security.dto.category.CategoryResponse;
import project.product.spring.security.services.CategoryService;

@RestController
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest));
    }

    @GetMapping("/findAll")
    public  ResponseEntity<Page<CategoryResponse>> getAllCategory(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body( categoryService.findAll(pageable));
    }

    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryResponse> getOneCategory(@PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(categoryId));
    }

    @PutMapping("/{categoryId}")
    public  ResponseEntity<CategoryResponse> updateCategory(@PathVariable  Long categoryId, @RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(categoryId,categoryRequest));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteOneCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
