package ru.clevertec.mapper;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.entity.Category;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class CarMapper {

    private static CarMapper carMapper;

    public Car buildCar(HttpServletRequest request) {
        Category category = Category.builder()
                .id(Long.valueOf(request.getParameter("category id")))
                .build();

        CarShowroom carShowroom = CarShowroom.builder()
                .id(Long.valueOf(request.getParameter("car showroom id")))
                .build();

        return Car.builder()
                .model(request.getParameter("model"))
                .brand(request.getParameter("brand"))
                .year(request.getParameter("year"))
                .price(new BigDecimal(request.getParameter("price")))
                .category(category)
                .carShowroom(carShowroom)
                .build();
    }

    public static CarMapper getInstance() {
        if (carMapper == null) {
            carMapper = new CarMapper();
        }
        return carMapper;
    }

    private CarMapper() {
    }
}