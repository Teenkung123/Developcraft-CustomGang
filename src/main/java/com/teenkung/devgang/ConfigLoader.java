package com.teenkung.devgang;

public class ConfigLoader {

    private String databaseHost;
    private String databasePort;
    private String databaseUsername;
    private String databasePassword;
    private String databaseSSL;
    private String databaseName;
    private String databaseTablePrefix;

    public void loadConfig() {

        //create configuration file if not exists
        DevGang.getMainInstance().getConfig().options().copyDefaults();
        DevGang.getMainInstance().saveDefaultConfig();

        //load config

        databaseHost = DevGang.getMainInstance().getConfig().getString("Storage.Host", "localhost");
        databasePort = DevGang.getMainInstance().getConfig().getString("Storage.Port", "3306");
        databaseUsername = DevGang.getMainInstance().getConfig().getString("Storage.Username", "minecraft");
        databasePassword = DevGang.getMainInstance().getConfig().getString("Storage.Password", "minecraft");
        databaseSSL = DevGang.getMainInstance().getConfig().getString("Storage.SSL", "false");
        databaseName = DevGang.getMainInstance().getConfig().getString("Storage.Database", "database");
        databaseTablePrefix = DevGang.getMainInstance().getConfig().getString("Storage.TablePrefix", "DevGang_");
    }

    public String getDatabaseHost() { return databaseHost; }
    public String getDatabasePort() { return databasePort; }
    public String getDatabaseUsername() { return databaseUsername; }
    public String getDatabasePassword() { return databasePassword; }
    public String getDatabaseSSL() { return databaseSSL; }
    public String getDatabaseName() { return databaseName; }
    public String getDatabaseGangTable() { return databaseTablePrefix+"GangsData"; }
    public String getDatabasePlayerTable() { return databaseTablePrefix+"PlayerData"; }
}
