package de.linzn.mineSuite.core.configurations.YamlFiles;

import de.linzn.mineSuite.core.utils.CustomYamlConfig;

import java.util.ArrayList;
import java.util.List;

public class GeneralConfig {

    public String BUNGEE_SERVER_NAME;
    public List<String> DISABLED_WORLDS;

    public String JSOCKET_HOST;
    public int JSOCKET_PORT;


    public String SQL_HOST;
    public int SQL_PORT;
    public String SQL_USER;
    public String SQL_PASSWORD;
    public String SQL_DATABASE;
    public int TELEPORT_WARMUP;

    public GeneralConfig(CustomYamlConfig generalConfig) {
        BUNGEE_SERVER_NAME = (String) generalConfig.getConfigValue("general.bungeeServerName", "servernameInBungeecord");
        List<String> worldList = new ArrayList<>();
        worldList.add("disabledWorld");
        DISABLED_WORLDS = (List<String>) generalConfig.getConfigValue("general.disabledWorlds", worldList);

        JSOCKET_HOST = (String) generalConfig.getConfigValue("jSocket.hostname", "localhost");
        JSOCKET_PORT = (int) generalConfig.getConfigValue("jSocket.port", 9090);

        SQL_HOST = (String) generalConfig.getConfigValue("mysql.hostname", "localhost");
        SQL_PORT = (int) generalConfig.getConfigValue("mysql.port", 3306);
        SQL_USER = (String) generalConfig.getConfigValue("mysql.user", "minecraft");
        SQL_PASSWORD = (String) generalConfig.getConfigValue("mysql.password", "deinPW");
        SQL_DATABASE = (String) generalConfig.getConfigValue("mysql.database", "mineSuite");

        TELEPORT_WARMUP = (int) generalConfig.getConfigValue("teleport.warmup", 2);


    }
}
