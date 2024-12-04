package ru.clevertec.controller.car;

import ru.clevertec.entity.Car;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.service.CarService;
import ru.clevertec.service.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/car/update")
public class UpdateCarController extends HttpServlet {

    private final CarService carService = CarServiceImpl.getInstance();
    private final CarMapper carMapper = CarMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cars", carService.readCars());
        request.getRequestDispatcher("/pages/car/update-car.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car = carMapper.buildCar(request);
        car.setId(Long.valueOf(request.getParameter("id")));
        carService.updateCar(car);
        doGet(request, response);
    }
}