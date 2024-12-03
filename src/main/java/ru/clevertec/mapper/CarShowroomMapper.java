package ru.clevertec.mapper;

import ru.clevertec.entity.CarShowroom;

import javax.servlet.http.HttpServletRequest;

public class CarShowroomMapper {

    private static CarShowroomMapper carShowroomMapper;

    public CarShowroom buildCarShowroom(HttpServletRequest request) {
        return CarShowroom.builder()
                .name(request.getParameter("name"))
                .address(request.getParameter("address"))
                .build();
    }

    public static CarShowroomMapper getInstance() {
        if (carShowroomMapper == null) {
            carShowroomMapper = new CarShowroomMapper();
        }
        return carShowroomMapper;
    }

    private CarShowroomMapper() {

    }
}