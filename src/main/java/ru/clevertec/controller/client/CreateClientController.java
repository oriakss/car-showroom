package ru.clevertec.controller.client;

import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/client/create")
public class CreateClientController extends HttpServlet {

    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final ClientMapper clientMapper = ClientMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/client/create-client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clientService.createClient(clientMapper.buildClient(request));
        doGet(request, response);
    }
}