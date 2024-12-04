package ru.clevertec.repository;

import ru.clevertec.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> createCategory(Category category);

    Optional<List<Category>> readCategories();

    Optional<Category> updateCategory(Category updatedCategory);

    Optional<Category> deleteCategory(Long categoryId);
}