package de.alpharout.ownworlds.utils;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        String url = "jdbc:mariadb://" + host + "/" + database;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Log.error("MariaDB JDBC Driver not found! Please contact the developer.");
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(OwnWorlds.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    connection = DriverManager.getConnection(url, username, password);
                    Log.debug("Connected to database!");
                } catch (SQLException e) {
                    Log.error("Couldn't connect to database! Check the credentials in database.yml");
                }
            }
        });
    }
}
