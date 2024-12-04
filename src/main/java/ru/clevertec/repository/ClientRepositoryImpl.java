package ru.clevertec.repository;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Client;
import ru.clevertec.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private static ClientRepository clientRepository;
    private List<Client> clients = new ArrayList<>();

    @Override
    public Optional<Client> createClient(Client client) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            session.persist(client);

            CriteriaQuery<Long> clientCriteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Client> clientRoot = clientCriteriaQuery.from(Client.class);

            clientCriteriaQuery
                    .select(clientRoot.get("id"))
                    .where(criteriaBuilder.equal(clientRoot.get("name"), client.getName()));

            Long clientId = session.createQuery(clientCriteriaQuery)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow();

            transaction.commit();

            client.setId(clientId);
            clients.add(client);
            return Optional.of(client);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Client>> readClients() {
        try (Session session = HibernateUtil.getSession()) {
            if (clients == null || clients.isEmpty()) {
                Transaction transaction = session.beginTransaction();
                HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<Client> clientCriteriaQuery = criteriaBuilder.createQuery(Client.class);
                Root<Client> clientRoot = clientCriteriaQuery.from(Client.class);

                clientCriteriaQuery.select(clientRoot);
                clients = session.createQuery(clientCriteriaQuery).getResultList();

                transaction.commit();
            }
            return Optional.of(clients);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Client> updateClient(Client updatedClient) {
        System.out.println(updatedClient);
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Client> clientCriteriaQuery = criteriaBuilder.createQuery(Client.class);
            Root<Client> clientRoot = clientCriteriaQuery.from(Client.class);

            clientCriteriaQuery.select(clientRoot)
                    .where(criteriaBuilder.equal(clientRoot.get("id"), updatedClient.getId()));

            Client clientDB = session.createQuery(clientCriteriaQuery).getSingleResult();
            clientDB.setName(updatedClient.getName());
            clientDB.getContacts().addAll(updatedClient.getContacts());

            transaction.commit();

            Client client = clients.stream()
                    .filter(item -> Objects.equals(item.getId(), clientDB.getId()))
                    .findFirst()
                    .orElseThrow();
            int index = clients.indexOf(client);
            clients.remove(client);
            clients.add(index, clientDB);
            return Optional.of(client);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Client> deleteClient(Long clientId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaDelete<Client> clientCriteriaDelete = criteriaBuilder.createCriteriaDelete(Client.class);
            Root<Client> clientRoot = clientCriteriaDelete.from(Client.class);

            clientCriteriaDelete.where(criteriaBuilder.equal(clientRoot.get("id"), clientId));
            session.createMutationQuery(clientCriteriaDelete).executeUpdate();

            transaction.commit();

            Client client = clients.stream()
                    .filter(item -> Objects.equals(item.getId(), clientId))
                    .findFirst()
                    .orElseThrow();
            clients.remove(client);
            return Optional.of(client);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static ClientRepository getInstance() {
        if (clientRepository == null) {
            clientRepository = new ClientRepositoryImpl();
        }
        return clientRepository;
    }

    private ClientRepositoryImpl() {
    }
}