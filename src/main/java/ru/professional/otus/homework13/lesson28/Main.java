package ru.professional.otus.homework13.lesson28;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.professional.otus.homework13.lesson28.entity.Address;
import ru.professional.otus.homework13.lesson28.entity.Client;
import ru.professional.otus.homework13.lesson28.entity.Phone;
import ru.professional.otus.homework13.lesson28.util.LiquibaseRunner;

import java.util.List;
import java.util.UUID;

/**
 * @author Sergei on 14.12.2024 21:42.
 * @project Default (Template) Project
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static final String PERSISTENCE_UNIT_NAME = "SingleUnit";

    public static void main(String[] args) {
        /** Запуск миграций Liquibase */
        LiquibaseRunner.runMigrations();

        save();
        read();

    }

    public static void save() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager = emf.createEntityManager();

        UUID uuid = UUID.randomUUID();
        Client client = new Client("Person7");

        Phone phone = new Phone("8(950)999-99-09");
        Phone phone1 = new Phone("8(950)999-99-10");

        Address address = new Address("Вязов");

        phone.setClient(client);
        phone1.setClient(client);
        address.setClient(client);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(client);
        entityManager.persist(phone);
        entityManager.persist(phone1);
        entityManager.persist(address);

        transaction.commit();

        entityManager.close();
    }


    public static void read() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = emf.createEntityManager();

        List<Client> clientList = entityManager
                .createNativeQuery("select c.id, c.name_, a.street, p.number_ from clients c " +
                        "left join addresses a on c.id = a.client_id " +
                        "left join phones p on c.id = p.client_id " +
                        "order by c.name_", Client.class)
                .getResultList();

        clientList.forEach(client -> LOGGER.info("Client: {}, Street: {}, Phone: {} ", client.getName(),
                            client.getAddresses().getStreet(), client.getPhones()));


        entityManager.close();
    }

}