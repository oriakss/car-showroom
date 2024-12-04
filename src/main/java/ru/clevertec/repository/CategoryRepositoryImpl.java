package ru.clevertec.repository;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Category;
import ru.clevertec.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static CategoryRepository categoryRepository;
    private List<Category> categories = new ArrayList<>();

    @Override
    public Optional<Category> createCategory(Category category) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            session.persist(category);

            CriteriaQuery<Long> categoryCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Category> categoryRoot = categoryCriteriaQuery.from(Category.class);

            categoryCriteriaQuery
                    .select(categoryRoot.get("id"))
                    .where(criteriaBuilder.equal(categoryRoot.get("name"), category.getName()));

            Long categoryId = session.createQuery(categoryCriteriaQuery)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow();

            transaction.commit();

            category.setId(categoryId);
            categories.add(category);
            return Optional.of(category);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Category>> readCategories() {
        try (Session session = HibernateUtil.getSession()) {
            if (categories == null || categories.isEmpty()) {
                Transaction transaction = session.beginTransaction();
                HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<Category> categoryCriteriaQuery = criteriaBuilder.createQuery(Category.class);
                Root<Category> categoryRoot = categoryCriteriaQuery.from(Category.class);

                categoryCriteriaQuery.select(categoryRoot);
                categories = session.createQuery(categoryCriteriaQuery).getResultList();

                transaction.commit();
            }
            return Optional.of(categories);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Category> updateCategory(Category updatedCategory) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaUpdate<Category> categoryCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Category.class);
            Root<Category> categoryRoot = categoryCriteriaUpdate.from(Category.class);

            categoryCriteriaUpdate.set("name", updatedCategory.getName())
                    .where(criteriaBuilder.equal(categoryRoot.get("id"), updatedCategory.getId()));
            session.createMutationQuery(categoryCriteriaUpdate).executeUpdate();

            transaction.commit();

            Category category = categories.stream()
                    .filter(item -> Objects.equals(item.getId(), updatedCategory.getId()))
                    .findFirst()
                    .orElseThrow();
            int index = categories.indexOf(category);
            categories.remove(category);
            categories.add(index, updatedCategory);
            return Optional.of(category);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Category> deleteCategory(Long categoryId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaDelete<Category> categoryCriteriaDelete = criteriaBuilder.createCriteriaDelete(Category.class);
            Root<Category> categoryRoot = categoryCriteriaDelete.from(Category.class);

            categoryCriteriaDelete.where(criteriaBuilder.equal(categoryRoot.get("id"), categoryId));
            session.createMutationQuery(categoryCriteriaDelete).executeUpdate();

            transaction.commit();

            Category category = categories.stream()
                    .filter(item -> Objects.equals(item.getId(), categoryId))
                    .findFirst()
                    .orElseThrow();
            categories.remove(category);
            return Optional.of(category);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static CategoryRepository getInstance() {
        if (categoryRepository == null) {
            categoryRepository = new CategoryRepositoryImpl();
        }
        return categoryRepository;
    }

    private CategoryRepositoryImpl() {
        if(readCategories().orElseThrow().isEmpty()) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();

                Category category = new Category();
                category.setName("Sedan");

                session.persist(category);
                transaction.commit();
            }
        }
    }
}