package ru.clevertec.controller.category;

import ru.clevertec.service.CategoryService;
import ru.clevertec.service.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/category/delete")
public class DeleteCategoryController extends HttpServlet {

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryService.readCategories());
        request.getRequestDispatcher("/pages/category/delete-category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryService.deleteCategory(Long.valueOf(request.getParameter("id")));
        doGet(request, response);
    }
}