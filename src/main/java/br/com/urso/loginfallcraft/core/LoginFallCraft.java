package br.com.urso.loginfallcraft.core;

import br.com.urso.loginfallcraft.data.ConfigFile;
import net.md_5.bungee.api.plugin.Plugin;

public final class LoginFallCraft extends Plugin {
    public static LoginFallCraft pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;

        ConfigFile.setupConfigFile();

    }

    @Override
    public void onDisable() {

    }
}
