package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category) {
        Category updatedCategory = categoryRepository.findCategoryById(id);
        if (updatedCategory == null) {
            throw new ApiException("Category not found");
        }

        updatedCategory.setName(category.getName());

        categoryRepository.save(updatedCategory);
    }

    public void removeCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ApiException("Category not found");
        }

        categoryRepository.deleteById(id);
    }
}
