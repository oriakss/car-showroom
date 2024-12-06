package ru.clevertec.controller.client;

import ru.clevertec.entity.Client;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/client/update")
public class UpdateClientController extends HttpServlet {

    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final ClientMapper clientMapper = ClientMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clients", clientService.readClients());
        request.getRequestDispatcher("/pages/client/update-client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = clientMapper.buildClient(request);
        client.setId(Long.valueOf(request.getParameter("id")));
        clientService.updateClient(client);
        doGet(request, response);
    }
}