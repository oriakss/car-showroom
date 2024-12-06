package ru.clevertec.repository;

import ru.clevertec.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Optional<Review> createReview(Review review);

    Optional<List<Review>> readReviews();

    Optional<Review> updateReview(Review updatedReview);

    Optional<Review> deleteReview(Long reviewId);
}