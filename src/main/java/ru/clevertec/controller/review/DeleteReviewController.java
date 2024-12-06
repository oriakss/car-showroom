package ru.clevertec.controller.review;

import ru.clevertec.service.ReviewService;
import ru.clevertec.service.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/review/delete")
public class DeleteReviewController extends HttpServlet {

    private final ReviewService reviewService = ReviewServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("reviews", reviewService.readReviews());
        request.getRequestDispatcher("/pages/review/delete-review.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reviewService.deleteReview(Long.valueOf(request.getParameter("id")));
        doGet(request, response);
    }
}