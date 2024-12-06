package ru.clevertec.mapper;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;

import javax.servlet.http.HttpServletRequest;

public class ReviewMapper {

    private static ReviewMapper reviewMapper;

    public Review buildReview(HttpServletRequest request) {
        Client client = Client.builder()
                .id(Long.valueOf(request.getParameter("client id")))
                .build();

        Car car = Car.builder()
                .id(Long.valueOf(request.getParameter("car id")))
                .build();

        return Review.builder()
                .text(request.getParameter("text"))
                .rating(request.getParameter("rating"))
                .client(client)
                .car(car)
                .build();
    }

    public static ReviewMapper getInstance() {
        if (reviewMapper == null) {
            reviewMapper = new ReviewMapper();
        }
        return reviewMapper;
    }

    private ReviewMapper() {
    }
}