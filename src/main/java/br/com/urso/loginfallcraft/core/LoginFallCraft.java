package br.com.urso.loginfallcraft.core;

import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Handler;

public final class LoginFallCraft extends Plugin {
    public static LoginFallCraft pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;
    }

    @Override
    public void onDisable() {

    }
}
