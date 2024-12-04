package ru.clevertec.repository;

import ru.clevertec.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Optional<Client> createClient(Client client);

    Optional<List<Client>> readClients();

    Optional<Client> updateClient(Client updatedClient);

    Optional<Client> deleteClient(Long clientId);
}