package ru.clevertec.service;

import ru.clevertec.entity.Car;

import java.util.List;

public interface CarService {

    void createCar(Car car);

    List<Car> readCars();

    void updateCar(Car car);

    void deleteCar(Long carId);
}