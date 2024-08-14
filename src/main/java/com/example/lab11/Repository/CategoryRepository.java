package com.example.lab11.Repository;

import com.example.lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);
}
