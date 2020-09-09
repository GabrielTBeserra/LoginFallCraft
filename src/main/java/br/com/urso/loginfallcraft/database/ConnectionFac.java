package br.com.urso.loginfallcraft.database;


import br.com.urso.loginfallcraft.core.LoginFallCraft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFac {
    private static Connection connection = null;
    private final LoginFallCraft pl = LoginFallCraft.pluginInstance;

    private ConnectionFac() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + pl.getDataFolder() + "/slogin.db");
    }

    public static Connection getConnection() {

        if (connection == null) {
            try {
                new ConnectionFac();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}