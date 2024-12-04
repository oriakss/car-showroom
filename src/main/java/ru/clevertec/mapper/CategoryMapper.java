package ru.clevertec.mapper;

import ru.clevertec.entity.Category;

import javax.servlet.http.HttpServletRequest;

public class CategoryMapper {

    private static CategoryMapper categoryMapper;

    public Category buildCategory(HttpServletRequest request) {
        return Category.builder()
                .name(request.getParameter("name"))
                .build();
    }

    public static CategoryMapper getInstance() {
        if (categoryMapper == null) {
            categoryMapper = new CategoryMapper();
        }
        return categoryMapper;
    }

    private CategoryMapper() {
    }
}