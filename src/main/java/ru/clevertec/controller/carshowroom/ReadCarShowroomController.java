package ru.clevertec.controller.carshowroom;

import ru.clevertec.service.CarShowroomService;
import ru.clevertec.service.CarShowroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/read")
public class ReadCarShowroomController  extends HttpServlet {

    private final CarShowroomService carShowroomService = CarShowroomServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carShowrooms", carShowroomService.readCarShowrooms());
        request.getRequestDispatcher("/pages/car-showroom/read-car-showrooms.jsp").forward(request, response);
    }
}