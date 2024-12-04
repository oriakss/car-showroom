package ru.clevertec.controller.carshowroom;

import ru.clevertec.service.CarShowroomService;
import ru.clevertec.service.CarShowroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/car-showroom/delete")
public class DeleteCarShowroomController  extends HttpServlet {

    private final CarShowroomService carShowroomService = CarShowroomServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carShowrooms", carShowroomService.readCarShowrooms());
        request.getRequestDispatcher("/pages/car-showroom/delete-car-showroom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carShowroomService.deleteCarShowroom(Long.valueOf(request.getParameter("id")));
        doGet(request, response);
    }
}