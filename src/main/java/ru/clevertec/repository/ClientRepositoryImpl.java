package ru.clevertec.repository;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Client;
import ru.clevertec.util.HibernateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ClientRepositoryImpl implements ClientRepository {

    private static ClientRepository clientRepository;
    private List<Client> clients;

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
                    .where(criteriaBuilder.equal(clientRoot.get("name"), client.getName()))
                    .where(criteriaBuilder.equal(clientRoot.get("registrationDate"), client.getRegistrationDate()));

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
        if (clients == null) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<Client> clientCriteriaQuery = criteriaBuilder.createQuery(Client.class);
                Root<Client> clientRoot = clientCriteriaQuery.from(Client.class);

                clientCriteriaQuery.select(clientRoot);
                clients = session.createQuery(clientCriteriaQuery).getResultList();

                transaction.commit();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return Optional.of(clients);
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
        if (readClients().orElseThrow().isEmpty()) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();

                Client client = Client.builder()
                        .name("John Doe")
                        .contacts(Set.of("222-232", "77-88"))
                        .registrationDate(LocalDateTime.of(2012, 12, 12, 12, 12, 12, 121212))
                        .build();

                session.persist(client);
                transaction.commit();

                clients.add(client);
            }
        }
    }
}