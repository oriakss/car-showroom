package ru.clevertec.mapper;

import ru.clevertec.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ClientMapper {

    private static ClientMapper clientMapper;

    public Client buildClient(HttpServletRequest request) {
        String contact = request.getParameter("contacts");
        Set<String> contacts = new HashSet<>();
        boolean isNotBlankContact = !contact.isBlank();
        if (isNotBlankContact) {
            contacts.add(contact);
        }
        return Client.builder()
                .name(request.getParameter("name"))
                .registrationDate(LocalDateTime.now())
                .contacts(contacts)
                .build();
    }

    public static ClientMapper getInstance() {
        if (clientMapper == null) {
            clientMapper = new ClientMapper();
        }
        return clientMapper;
    }

    private ClientMapper() {
    }
}