package ru.clevertec.service;

import ru.clevertec.entity.Category;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.repository.CategoryRepositoryImpl;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static CategoryService categoryService;
    private final CategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();

    @Override
    public void createCategory(Category category) {
        categoryRepository.createCategory(category).orElseThrow();
    }

    @Override
    public List<Category> readCategories() {
        return categoryRepository.readCategories().orElseThrow();
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category).orElseThrow();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteCategory(categoryId).orElseThrow();
    }

    public static CategoryService getInstance() {
        if (categoryService == null) {
            categoryService = new CategoryServiceImpl();
        }
        return categoryService;
    }

    private CategoryServiceImpl() {
    }
}