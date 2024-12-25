package ru.professional.otus.homework13.lesson28.util;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseRunner.class);

    private LiquibaseRunner() {
    }

    public static void runMigrations() {
        LOGGER.info("LiquibaseRunner run...");
/*        String jdbcUrl = "jdbc:postgresql://localhost:5433/otus-db";
        String username = "user";
        String password = "password";*/

        String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/otus";
        String username = "java";
        String password = "java";

        String changelogFile = "db/changelog/db.changelog-master.yaml";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                    new liquibase.database.jvm.JdbcConnection(connection));
            Liquibase liquibase = new Liquibase(changelogFile, new ClassLoaderResourceAccessor(), database);
            liquibase.update("");
            LOGGER.info("Liquibase migrations applied successfully.");
        } catch (SQLException | LiquibaseException e) {
            LOGGER.error("Error while running Liquibase migrations", e);
            throw new RuntimeException("Error while running Liquibase migrations", e);
        }
    }
}
