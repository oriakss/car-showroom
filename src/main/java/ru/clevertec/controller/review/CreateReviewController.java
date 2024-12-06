package ru.clevertec.controller.review;

import ru.clevertec.mapper.ReviewMapper;
import ru.clevertec.service.ReviewService;
import ru.clevertec.service.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/review/create")
public class CreateReviewController extends HttpServlet {

    private final ReviewService reviewService = ReviewServiceImpl.getInstance();
    private final ReviewMapper reviewMapper = ReviewMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/review/create-review.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reviewService.createReview(reviewMapper.buildReview(request));
        doGet(request, response);
    }
}