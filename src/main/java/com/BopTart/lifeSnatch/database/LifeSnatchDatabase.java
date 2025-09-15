package com.BopTart.lifeSnatch.database;

import org.bukkit.entity.Player;

import java.sql.*;

public class LifeSnatchDatabase {
    private static LifeSnatchDatabase instance;
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
        instance = this;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }

    public void addPlayer(Player player) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO players (uuid, username) VALUES (?, ?)")){
            statement.setString(1, player.getUniqueId().toString());
            statement.setString(2, player.getDisplayName());
            statement.executeUpdate();
        }
    }

    public boolean playerExists(Player player) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE uuid = ?")){
            statement.setString(1, player.getUniqueId().toString());
            ResultSet rs = statement.executeQuery();
            return rs.next();
        }
    }

    public void updatePlayerHearts(Player player, double hearts) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE players SET hearts = ? WHERE uuid = ?")){
            statement.setDouble(1, hearts);
            statement.setString(2, player.getUniqueId().toString());
            statement.executeUpdate();
        }
    }

    public double getPlayerHearts(Player player) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT hearts FROM players WHERE uuid = ?")){
            statement.setString(1, player.getUniqueId().toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return rs.getDouble("hearts");
            } else {
                return 0;
            }
        }
    }

    public static LifeSnatchDatabase getDatabase(){
        return instance;
    }
}
