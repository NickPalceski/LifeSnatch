package com.BopTart.lifeSnatch.database;

import com.BopTart.lifeSnatch.LifeSnatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LifeSnatchDatabase {

    private final Connection connection;

    public LifeSnatchDatabase(String path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);

        try (Statement statement = connection.createStatement()){
            statement.execute("""
                CREATE TABLE IF NOT EXISTS players (
                    uuid TEXT PRIMARY KEY,
                    username TEXT NOT NULL,
                    hearts DOUBLE NOT NULL DEFAULT 20.0
                )
        """);
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
