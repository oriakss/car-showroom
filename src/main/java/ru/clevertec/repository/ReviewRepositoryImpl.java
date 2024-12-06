package ru.clevertec.repository;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Review;
import ru.clevertec.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ReviewRepositoryImpl implements ReviewRepository {

    private static ReviewRepository reviewRepository;
    private List<Review> reviews = new ArrayList<>();

    @Override
    public Optional<Review> createReview(Review review) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            session.persist(review);

            CriteriaQuery<Long> reviewCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Review> reviewRoot = reviewCriteriaQuery.from(Review.class);

            reviewCriteriaQuery
                    .select(reviewRoot.get("id"))
                    .where(criteriaBuilder.equal(reviewRoot.get("text"), review.getText()));

            Long reviewId = session.createQuery(reviewCriteriaQuery)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow();

            transaction.commit();

            review.setId(reviewId);
            reviews.add(review);
            return Optional.of(review);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Review>> readReviews() {
        try (Session session = HibernateUtil.getSession()) {
            if (reviews == null || reviews.isEmpty()) {
                Transaction transaction = session.beginTransaction();
                HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<Review> reviewCriteriaQuery = criteriaBuilder.createQuery(Review.class);
                Root<Review> reviewRoot = reviewCriteriaQuery.from(Review.class);

                reviewCriteriaQuery.select(reviewRoot);
                reviews = session.createQuery(reviewCriteriaQuery).getResultList();

                transaction.commit();
            }
            return Optional.of(reviews);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Review> updateReview(Review updatedReview) {
        System.out.println(updatedReview);
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaUpdate<Review> reviewCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Review.class);
            Root<Review> reviewRoot = reviewCriteriaUpdate.from(Review.class);

            reviewCriteriaUpdate.set("text", updatedReview.getText())
                    .set("rating", updatedReview.getRating())
                    .where(criteriaBuilder.equal(reviewRoot.get("id"), updatedReview.getId()));
            session.createMutationQuery(reviewCriteriaUpdate).executeUpdate();

            transaction.commit();

            Review review = reviews.stream()
                    .filter(item -> Objects.equals(item.getId(), updatedReview.getId()))
                    .findFirst()
                    .orElseThrow();
            int index = reviews.indexOf(review);
            reviews.remove(review);
            reviews.add(index, updatedReview);
            return Optional.of(review);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Review> deleteReview(Long reviewId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaDelete<Review> reviewCriteriaDelete = criteriaBuilder.createCriteriaDelete(Review.class);
            Root<Review> reviewRoot = reviewCriteriaDelete.from(Review.class);

            reviewCriteriaDelete.where(criteriaBuilder.equal(reviewRoot.get("id"), reviewId));
            session.createMutationQuery(reviewCriteriaDelete).executeUpdate();

            transaction.commit();

            Review review = reviews.stream()
                    .filter(item -> Objects.equals(item.getId(), reviewId))
                    .findFirst()
                    .orElseThrow();
            reviews.remove(review);
            return Optional.of(review);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static ReviewRepository getInstance() {
        if (reviewRepository == null) {
            reviewRepository = new ReviewRepositoryImpl();
        }
        return reviewRepository;
    }

    private ReviewRepositoryImpl() {
    }
}