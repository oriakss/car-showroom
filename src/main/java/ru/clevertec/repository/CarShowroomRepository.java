package ru.clevertec.repository;

import ru.clevertec.entity.CarShowroom;

import java.util.List;
import java.util.Optional;

public interface CarShowroomRepository {

    Optional<CarShowroom> createCarShowroom(CarShowroom carShowroom);

    Optional<List<CarShowroom>> readCarShowrooms();

    Optional<CarShowroom> updateCarShowroom(CarShowroom updatedCarShowroom);

    Optional<CarShowroom> deleteCarShowroom(Long carShowroomId);
}