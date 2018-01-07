package de.linzn.mineSuite.core.configurations;

import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralConfig;
import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralLanguage;
import de.linzn.mineSuite.core.utils.CustomYamlConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MineConfigs {
    public GeneralConfig generalConfig;
    public GeneralLanguage generalLanguage;
    private CustomYamlConfig generalConfigFile;
    private CustomYamlConfig generalLanguageFile;


    public MineConfigs(JavaPlugin plugin) {
        File configDirectory = new File(plugin.getDataFolder(), "configs");
        if (!configDirectory.exists()) {
            configDirectory.mkdir();
        }


        /* Configs */
        this.generalConfigFile = new CustomYamlConfig(plugin, plugin.getDataFolder(), "general.yml");
        this.generalConfig = new GeneralConfig(this.generalConfigFile);
        this.generalConfigFile.saveAndReload();



        /* Languages */
        generalLanguageFile = new CustomYamlConfig(plugin, plugin.getDataFolder(), "languageFile.yml");
        this.generalLanguage = new GeneralLanguage(this.generalLanguageFile);
        generalLanguageFile.saveAndReload();

    }
}
