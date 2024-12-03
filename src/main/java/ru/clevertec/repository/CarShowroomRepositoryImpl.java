package ru.clevertec.repository;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarShowroomRepositoryImpl implements CarShowroomRepository {

    private static CarShowroomRepository carShowroomRepository;
    private List<CarShowroom> carShowrooms = new ArrayList<>();

    @Override
    public Optional<CarShowroom> createCarShowroom(CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            session.persist(carShowroom);

            CriteriaQuery<Long> carShowroomCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<CarShowroom> carShowroomRoot = carShowroomCriteriaQuery.from(CarShowroom.class);

            carShowroomCriteriaQuery
                    .select(carShowroomRoot.get("id"))
                    .where(criteriaBuilder.equal(carShowroomRoot.get("name"), carShowroom.getName()));

            Long carShowroomId = session.createQuery(carShowroomCriteriaQuery)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow();

            transaction.commit();

            carShowroom.setId(carShowroomId);
            carShowrooms.add(carShowroom);
            return Optional.of(carShowroom);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<CarShowroom>> readCarShowrooms() {
        try (Session session = HibernateUtil.getSession()) {
            if (carShowrooms == null || carShowrooms.isEmpty()) {
                Transaction transaction = session.beginTransaction();
                HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<CarShowroom> carShowroomCriteriaQuery = criteriaBuilder.createQuery(CarShowroom.class);
                Root<CarShowroom> carShowroomRoot = carShowroomCriteriaQuery.from(CarShowroom.class);

                carShowroomCriteriaQuery.select(carShowroomRoot);
                carShowrooms = session.createQuery(carShowroomCriteriaQuery).getResultList();

                transaction.commit();
            }
            return Optional.of(carShowrooms);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<CarShowroom> updateCarShowroom(CarShowroom updatedCarShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaUpdate<CarShowroom> carShowroomCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(CarShowroom.class);
            Root<CarShowroom> carShowroomRoot = carShowroomCriteriaUpdate.from(CarShowroom.class);

            carShowroomCriteriaUpdate.set("name", updatedCarShowroom.getName())
                    .set("address", updatedCarShowroom.getAddress())
                    .where(criteriaBuilder.equal(carShowroomRoot.get("id"), updatedCarShowroom.getId()));
            session.createMutationQuery(carShowroomCriteriaUpdate).executeUpdate();

            transaction.commit();

            CarShowroom carShowroom = carShowrooms.stream()
                    .filter(item -> Objects.equals(item.getId(), updatedCarShowroom.getId()))
                    .findFirst()
                    .orElseThrow();
            int index = carShowrooms.indexOf(carShowroom);
            carShowrooms.remove(carShowroom);
            carShowrooms.add(index, updatedCarShowroom);
            return Optional.of(carShowroom);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<CarShowroom> deleteCarShowroom(Long carShowroomId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaDelete<CarShowroom> carShowroomCriteriaDelete = criteriaBuilder.createCriteriaDelete(CarShowroom.class);
            Root<CarShowroom> carShowroomRoot = carShowroomCriteriaDelete.from(CarShowroom.class);

            carShowroomCriteriaDelete.where(criteriaBuilder.equal(carShowroomRoot.get("id"), carShowroomId));
            session.createMutationQuery(carShowroomCriteriaDelete).executeUpdate();

            transaction.commit();

            CarShowroom carShowroom = carShowrooms.stream()
                    .filter(item -> Objects.equals(item.getId(), carShowroomId))
                    .findFirst()
                    .orElseThrow();
            carShowrooms.remove(carShowroom);
            return Optional.of(carShowroom);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static CarShowroomRepository getInstance() {
        if (carShowroomRepository == null) {
            carShowroomRepository = new CarShowroomRepositoryImpl();
        }
        return carShowroomRepository;
    }

    private CarShowroomRepositoryImpl() {
        if(readCarShowrooms().orElseThrow().isEmpty()) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();

                CarShowroom carShowroom = new CarShowroom();
                carShowroom.setName("Car Showroom standard");
                carShowroom.setAddress("99 Union St");

                session.persist(carShowroom);
                transaction.commit();
            }
        }
    }
}