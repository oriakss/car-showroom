package ru.clevertec.service;

import ru.clevertec.entity.Category;

import java.util.List;

public interface CategoryService {

    void createCategory(Category category);

    List<Category> readCategories();

    void updateCategory(Category category);

    void deleteCategory(Long categoryId);
}