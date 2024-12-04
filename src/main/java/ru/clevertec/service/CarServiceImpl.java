package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.CarRepositoryImpl;

import java.util.List;

public class CarServiceImpl implements CarService {

    private static CarService carService;
    private final CarRepository carRepository = CarRepositoryImpl.getInstance();

    @Override
    public void createCar(Car car) {
        carRepository.createCar(car).orElseThrow();
    }

    @Override
    public List<Car> readCars() {
        return carRepository.readCars().orElseThrow();
    }

    @Override
    public void updateCar(Car car) {
        carRepository.updateCar(car).orElseThrow();
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteCar(carId).orElseThrow();
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarServiceImpl();
        }
        return carService;
    }

    private CarServiceImpl() {
    }
}