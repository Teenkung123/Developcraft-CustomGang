package com.teenkung.devgang;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DevGang extends JavaPlugin {

    private static DevGang MainInstance;
    private static ConfigLoader ConfigLoaderInstance;
    private Database databaseInstance;
    private static Connection connection;


    @Override
    public void onEnable() {
        loadInstance();
        ConfigLoaderInstance.loadConfig();
        try {
            databaseInstance.Connect();
            connection = databaseInstance.getConnection();
            databaseInstance.createTable();
        } catch (SQLException e) {
            System.out.println(colorize("&cSomething went wrong with MySQL Database Connection!\n&cTry check MySQL Database configuration!"));
        }


        Bukkit.getScheduler().runTaskLater(this, GangManager::addAllGang, 1);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadInstance() {
        MainInstance = this;
        ConfigLoaderInstance = new ConfigLoader();
        databaseInstance = new Database();
    }

    public static DevGang getMainInstance() { return MainInstance; }

    public static ConfigLoader getConfigLoaderInstance() {return ConfigLoaderInstance; }
    public Database getDatabase() { return databaseInstance; }
    public static Connection getDatabaseConnection() { return connection; }

    public static String colorize(String message) {
        if (message == null) {
            message = "";
        }
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
