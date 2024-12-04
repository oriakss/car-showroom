package ru.clevertec.repository;

import ru.clevertec.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Optional<Car> createCar(Car car);

    Optional<List<Car>> readCars();

    Optional<Car> updateCar(Car updatedCar);

    Optional<Car> deleteCar(Long carId);
}