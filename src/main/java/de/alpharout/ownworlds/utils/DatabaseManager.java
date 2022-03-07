package de.alpharout.ownworlds.utils;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class DatabaseManager {

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    public DatabaseManager(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Log.error("MariaDB JDBC Driver not found! Please contact the developer.");
            return;
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            Log.debug("Connected to database!");
            initTables();
        } catch (SQLException e) {
            Log.error("Couldn't connect to database! Check the credentials in database.yml");
        }
    }

    private void initTables() {
        try {
            PreparedStatement worldTableStatement = OwnWorlds.getDatabaseManager().getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS ownworlds_level (" +
                            "levelUUID varchar(255), " +
                            "ownerUUID varchar(255), " +
                            "serverName varchar(255)" +
                            ");"
            );
            worldTableStatement.executeUpdate();

            PreparedStatement serverTableStatement = OwnWorlds.getDatabaseManager().getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS servers (" +
                            "serverName varchar(255) " +
                            ");"
            );
            serverTableStatement.executeUpdate();

        } catch (SQLException sqle) {
            Log.error("Error sending statement to database! Couldn't create default tables in database.");
        }
    }


    public void createWorldEntry(UUID levelUUID, UUID ownerUUID, String serverName) {

        Bukkit.getScheduler().runTaskAsynchronously(OwnWorlds.getInstance(), new Runnable() {

            @Override
            public void run() {

                try {
                    PreparedStatement preparedStatement = OwnWorlds.getDatabaseManager().getConnection().prepareStatement(
                            "INSERT INTO ownworlds_level (" +
                                    "levelUUID, ownerUUID, serverName)" +
                                    " values (?, ?, ?);"
                    );

                    preparedStatement.setString(1, levelUUID.toString());
                    preparedStatement.setString(2, ownerUUID.toString());
                    preparedStatement.setString(3, serverName);

                    preparedStatement.executeUpdate();

                } catch (SQLException sqle) {
                    Log.error("Error sending statement to database! Couldn't create entry for world -> " + levelUUID.toString());
                }

            }
        });

    }

    public void registerServer(String serverName) {

        Bukkit.getScheduler().runTaskAsynchronously(OwnWorlds.getInstance(), new Runnable() {

            @Override
            public void run() {
                try {
                    PreparedStatement preparedStatement = OwnWorlds.getDatabaseManager().getConnection().prepareStatement("INSERT INTO servers (serverName) values (?);");

                    preparedStatement.setString(1, serverName);

                    preparedStatement.executeUpdate();
                    Log.info("Server " + serverName + " was registered in database");
                } catch (SQLException sqle) {
                    Log.error("Error sending statement to database! Couldn't register Server -> " + serverName);
                }

            }
        });
    }


    public Connection getConnection() {
        return connection;
    }


}
