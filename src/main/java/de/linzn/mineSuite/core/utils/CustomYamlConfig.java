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

package de.linzn.mineSuite.core.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomYamlConfig extends YamlConfiguration {

    private File file;
    private String defaults;
    private JavaPlugin plugin;

    /**
     * Creates new PluginFile, with defaults
     *
     * @param plugin   - Your plugin
     * @param fileName - Name of the file
     */
    public CustomYamlConfig(JavaPlugin plugin, File directory, String fileName) {
        this(plugin, directory, fileName, null);
    }

    public CustomYamlConfig(JavaPlugin plugin, File directory, String fileName, String defaultsName) {
        this.plugin = plugin;
        this.defaults = defaultsName;
        this.file = new File(directory, fileName);
        reload();
    }

    /**
     * Reload configuration
     */
    public void reload() {

        if (!file.exists()) {

            try {
                file.getParentFile().mkdirs();
                file.createNewFile();

            } catch (IOException exception) {
                exception.printStackTrace();
                plugin.getLogger().severe("Error while creating file " + file.getName());
            }

        }

        try {
            load(file);

            if (defaults != null) {
                InputStreamReader reader = new InputStreamReader(plugin.getResource(defaults));
                FileConfiguration defaultsConfig = YamlConfiguration.loadConfiguration(reader);

                setDefaults(defaultsConfig);
                options().copyDefaults(true);

                reader.close();
                save();
            }

        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
            plugin.getLogger().severe("Error while loading file " + file.getName());

        }

    }

    /**
     * Save configuration
     */
    public void save() {

        try {
            options().indent(2);
            save(file);

        } catch (IOException exception) {
            exception.printStackTrace();
            plugin.getLogger().severe("Error while saving file " + file.getName());
        }

    }

    public void saveAndReload() {
        save();
        reload();
    }

    public Object getConfigValue(String path, Object defaultValue) {
        if (!this.contains(path)) {
            this.set(path, defaultValue);
        }
        return this.get(path);

    }

}