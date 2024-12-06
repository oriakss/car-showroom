package ru.clevertec.repository;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.entity.Category;
import ru.clevertec.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {

    private static CarRepository carRepository;
    private List<Car> cars;

    @Override
    public Optional<Car> createCar(Car car) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            session.persist(car);

            CriteriaQuery<Long> carCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);

            carCriteriaQuery
                    .select(carRoot.get("id"))
                    .where(criteriaBuilder.equal(carRoot.get("model"), car.getModel()))
                    .where(criteriaBuilder.equal(carRoot.get("brand"), car.getBrand()));

            Long carId = session.createQuery(carCriteriaQuery)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow();

            transaction.commit();

            car.setId(carId);
            cars.add(car);
            return Optional.of(car);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Car>> readCars() {
        if (cars == null) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
                Root<Car> carRoot = carCriteriaQuery.from(Car.class);

                carCriteriaQuery.select(carRoot);
                cars = session.createQuery(carCriteriaQuery).getResultList();

                transaction.commit();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return Optional.of(cars);
    }

    @Override
    public Optional<Car> updateCar(Car updatedCar) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaUpdate<Car> carCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Car.class);
            Root<Car> carRoot = carCriteriaUpdate.from(Car.class);

            carCriteriaUpdate.set("model", updatedCar.getModel())
                    .set("brand", updatedCar.getBrand())
                    .set("year", updatedCar.getYear())
                    .set("price", updatedCar.getPrice())
                    .where(criteriaBuilder.equal(carRoot.get("id"), updatedCar.getId()));
            session.createMutationQuery(carCriteriaUpdate).executeUpdate();

            transaction.commit();

            Car car = cars.stream()
                    .filter(item -> Objects.equals(item.getId(), updatedCar.getId()))
                    .findFirst()
                    .orElseThrow();
            int index = cars.indexOf(car);
            cars.remove(car);
            cars.add(index, updatedCar);
            return Optional.of(car);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Car> deleteCar(Long carId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaDelete<Car> carCriteriaDelete = criteriaBuilder.createCriteriaDelete(Car.class);
            Root<Car> carRoot = carCriteriaDelete.from(Car.class);

            carCriteriaDelete.where(criteriaBuilder.equal(carRoot.get("id"), carId));
            session.createMutationQuery(carCriteriaDelete).executeUpdate();

            transaction.commit();

            Car car = cars.stream()
                    .filter(item -> Objects.equals(item.getId(), carId))
                    .findFirst()
                    .orElseThrow();
            cars.remove(car);
            return Optional.of(car);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static CarRepository getInstance() {
        if (carRepository == null) {
            carRepository = new CarRepositoryImpl();
        }
        return carRepository;
    }

    private CarRepositoryImpl() {
        if (readCars().orElseThrow().isEmpty()) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();

                Category category = CategoryRepositoryImpl.getInstance()
                        .readCategories()
                        .orElseThrow()
                        .stream()
                        .filter(item -> item.getName().equals("Sedan"))
                        .findFirst()
                        .orElseThrow();

                CarShowroom carShowroom = CarShowroomRepositoryImpl.getInstance()
                        .readCarShowrooms()
                        .orElseThrow()
                        .stream()
                        .filter(item -> item.getName().equals("Car Showroom standard"))
                        .findFirst()
                        .orElseThrow();

                Car car = new Car();
                car.setModel("X");
                car.setBrand("Toyota");
                car.setYear("2015");
                car.setPrice(BigDecimal.valueOf(10449.99));
                car.setCategory(category);
                car.setCarShowroom(carShowroom);

                session.persist(car);
                transaction.commit();

                cars.add(car);
            }
        }
    }
}