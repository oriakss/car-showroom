package ru.clevertec.service;

import ru.clevertec.entity.Review;

import java.util.List;

public interface ReviewService {

    void createReview(Review review);

    List<Review> readReviews();

    void updateReview(Review review);

    void deleteReview(Long reviewId);
}