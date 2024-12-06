package ru.clevertec.controller.client;

import ru.clevertec.service.ClientService;
import ru.clevertec.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/client/read")
public class ReadClientController extends HttpServlet {

    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clients", clientService.readClients());
        request.getRequestDispatcher("/pages/client/read-client.jsp").forward(request, response);
    }
}