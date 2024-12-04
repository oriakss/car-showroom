package ru.clevertec.controller.category;

import ru.clevertec.mapper.CategoryMapper;
import ru.clevertec.service.CategoryService;
import ru.clevertec.service.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/category/create")
public class CreateCategoryController extends HttpServlet {

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/category/create-category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryService.createCategory(categoryMapper.buildCategory(request));
        doGet(request, response);
    }
}