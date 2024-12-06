package ru.clevertec.service;

import ru.clevertec.entity.Client;

import java.util.List;

public interface ClientService {

    void createClient(Client client);

    List<Client> readClients();

    void updateClient(Client client);

    void deleteClient(Long clientId);
}