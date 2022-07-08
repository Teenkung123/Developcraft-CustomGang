package com.teenkung.devgang;

import java.sql.*;

import static com.teenkung.devgang.DevGang.colorize;

public class Database {

    /* Database Plan

    Split into 2 table first one is player data and second one is gang data
    Player Data: UUID, CurrentGang, GangPermission, GangChatSpy
    Gang Data: GangName, GangDisplayName, GangLevel, BanList

     */

    private Connection connection;

    public void Connect() throws SQLException {
        System.out.println(colorize("&aConnecting to MySQL!"));

        connection = DriverManager.getConnection(
                "jdbc:mysql://" + DevGang.getConfigLoaderInstance().getDatabaseHost() + ":" + DevGang.getConfigLoaderInstance().getDatabasePort() + "/" + DevGang.getConfigLoaderInstance().getDatabaseName() + "?useSSL="+DevGang.getConfigLoaderInstance().getDatabaseSSL(),
                DevGang.getConfigLoaderInstance().getDatabaseUsername(),
                DevGang.getConfigLoaderInstance().getDatabasePassword()
        );
    }

    public Connection getConnection() { return connection; }

    public void Disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return connection != null;
    }


    //This method is for creating MySQL Database table
    public void createTable() {
        try {

            //send console output
            System.out.println(colorize("&aChecking MySQL Table!"));

            //This creates Gang Data Table
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    DevGang.getConfigLoaderInstance().getDatabaseGangTable() +
                    " (" +
                    "ID int NOT NULL AUTO_INCREMENT, " +
                    "GangName MEDIUMTEXT, " +
                    "GangDisplayName MEDIUMTEXT, " +
                    "GangLevel BIGINT(40), " +
                    "BanList MEDIUMTEXT, " +
                    "PRIMARY KEY (ID)" +
                    ")");
            //close statement too
            statement.close();

            //This creates Player Data Table
            statement2.executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    DevGang.getConfigLoaderInstance().getDatabasePlayerTable() +
                    " (" +
                    "ID int NOT NULL AUTO_INCREMENT, " +
                    "UUID MEDIUMTEXT, " +
                    "GangLevel BIGINT(40), " +
                    "CurrentGang int, " +
                    "GangPermission MEDIUMTEXT, " +
                    "GangChatSpy BOOLEAN, " +
                    "PRIMARY KEY (ID)" +
                    ")");
            //close statement too
            statement2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
