package project.product.spring.security.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.product.spring.security.dto.category.CategoryRequest;
import project.product.spring.security.dto.category.CategoryResponse;
import project.product.spring.security.entity.Category;
import project.product.spring.security.mapper.CategoryMapper;
import project.product.spring.security.repository.CategoryRepository;
import project.product.spring.security.exception.ResourceNotFound;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return mapper.toResponse(categoryRepository.save(mapper.toEntity(categoryRequest)));
    }

    public Page<CategoryResponse> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return mapper.toResponse(categories);
    }

    public CategoryResponse findById(Long categoryId) {
        return mapper.toResponse(categoryRepository.findById(categoryId).orElseThrow(() -> {
            return new ResourceNotFound(categoryId);
        }));
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.findById(categoryId).isPresent()) throw new ResourceNotFound(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        try {
            Category categoryId = categoryRepository.getReferenceById(id);
            updateCategory(categoryId, categoryRequest);
            categoryRepository.save(categoryId);
            return new CategoryResponse(categoryId);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFound(id);
        }
    }

    private void updateCategory(Category categoryId, CategoryRequest categoryRequest) {
        categoryId.setName(categoryId.getName());
    }
}
