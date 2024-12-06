package ru.clevertec.controller.review;

import ru.clevertec.entity.Review;
import ru.clevertec.mapper.ReviewMapper;
import ru.clevertec.service.ReviewService;
import ru.clevertec.service.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/review/update")
public class UpdateReviewController extends HttpServlet {

    private final ReviewService reviewService = ReviewServiceImpl.getInstance();
    private final ReviewMapper reviewMapper = ReviewMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("reviews", reviewService.readReviews());
        request.getRequestDispatcher("/pages/review/update-review.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review = reviewMapper.buildReview(request);
        review.setId(Long.valueOf(request.getParameter("id")));
        reviewService.updateReview(review);
        doGet(request, response);
    }
}