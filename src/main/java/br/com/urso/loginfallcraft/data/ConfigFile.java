package br.com.urso.loginfallcraft.data;

import br.com.urso.loginfallcraft.core.LoginFallCraftBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigFile {
    public static void setupConfigFile() {
        if (!LoginFallCraftBungee.pluginInstance.getDataFolder().exists())
            LoginFallCraftBungee.pluginInstance.getDataFolder().mkdir();

        File file = new File(LoginFallCraftBungee.pluginInstance.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = LoginFallCraftBungee.pluginInstance.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConfigFile() {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(LoginFallCraftBungee.pluginInstance.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveConfig(Configuration configuration) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(LoginFallCraftBungee.pluginInstance.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
