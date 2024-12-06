package ru.clevertec.service;

import ru.clevertec.entity.Client;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.repository.ClientRepositoryImpl;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static ClientService clientService;
    private final ClientRepository clientRepository = ClientRepositoryImpl.getInstance();

    @Override
    public void createClient(Client client) {
        clientRepository.createClient(client).orElseThrow();
    }

    @Override
    public List<Client> readClients() {
        return clientRepository.readClients().orElseThrow();
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.updateClient(client).orElseThrow();
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteClient(clientId).orElseThrow();
    }

    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientServiceImpl();
        }
        return clientService;
    }

    private ClientServiceImpl() {
    }
}