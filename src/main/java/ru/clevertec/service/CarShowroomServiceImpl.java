package ru.clevertec.service;

import ru.clevertec.entity.CarShowroom;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.repository.CarShowroomRepositoryImpl;

import java.util.List;

public class CarShowroomServiceImpl implements CarShowroomService {

    private static CarShowroomService carShowroomService;
    private final CarShowroomRepository carShowroomRepository = CarShowroomRepositoryImpl.getInstance();

    @Override
    public void createCarShowroom(CarShowroom carShowroom) {
        carShowroomRepository.createCarShowroom(carShowroom).orElseThrow();
    }

    @Override
    public List<CarShowroom> readCarShowrooms() {
        return carShowroomRepository.readCarShowrooms().orElseThrow();
    }

    @Override
    public void updateCarShowroom(CarShowroom carShowroom) {
        carShowroomRepository.updateCarShowroom(carShowroom).orElseThrow();
    }

    @Override
    public void deleteCarShowroom(Long carShowroomId) {
        carShowroomRepository.deleteCarShowroom(carShowroomId).orElseThrow();
    }

    public static CarShowroomService getInstance() {
        if (carShowroomService == null) {
            carShowroomService = new CarShowroomServiceImpl();
        }
        return carShowroomService;
    }

    private CarShowroomServiceImpl() {
    }
}