package ru.clevertec.service;

import ru.clevertec.entity.CarShowroom;

import java.util.List;

public interface CarShowroomService {

    void createCarShowroom(CarShowroom carShowroom);

    List<CarShowroom> readCarShowrooms();

    void updateCarShowroom(CarShowroom carShowroom);

    void deleteCarShowroom(Long carShowroomId);
}