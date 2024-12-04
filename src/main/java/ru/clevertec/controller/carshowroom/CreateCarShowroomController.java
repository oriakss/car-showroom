package ru.clevertec.controller.carshowroom;

import ru.clevertec.mapper.CarShowroomMapper;
import ru.clevertec.service.CarShowroomService;
import ru.clevertec.service.CarShowroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/car-showroom/create")
public class CreateCarShowroomController extends HttpServlet {

    private final CarShowroomService carShowroomService = CarShowroomServiceImpl.getInstance();
    private final CarShowroomMapper carShowroomMapper = CarShowroomMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/car-showroom/create-car-showroom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carShowroomService.createCarShowroom(carShowroomMapper.buildCarShowroom(request));
        doGet(request, response);
    }
}