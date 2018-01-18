/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

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
