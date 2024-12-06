package ru.clevertec.service;

import ru.clevertec.entity.Review;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.repository.ReviewRepositoryImpl;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private static ReviewService reviewService;
    private ReviewRepository reviewRepository = ReviewRepositoryImpl.getInstance();

    @Override
    public void createReview(Review review) {
        reviewRepository.createReview(review).orElseThrow();
    }

    @Override
    public List<Review> readReviews() {
        return reviewRepository.readReviews().orElseThrow();
    }

    @Override
    public void updateReview(Review review) {
        reviewRepository.updateReview(review).orElseThrow();
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteReview(reviewId).orElseThrow();
    }

    public static ReviewService getInstance() {
        if (reviewService == null) {
            reviewService = new ReviewServiceImpl();
        }
        return reviewService;
    }

    private ReviewServiceImpl() {
    }
}