package ru.clevertec.controller.car;

import ru.clevertec.service.CarService;
import ru.clevertec.service.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/car/delete")
public class DeleteCarController extends HttpServlet {

    private final CarService carService = CarServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cars", carService.readCars());
        request.getRequestDispatcher("/pages/car/delete-car.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carService.deleteCar(Long.valueOf(request.getParameter("id")));
        doGet(request, response);
    }
}