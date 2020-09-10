package br.com.urso.loginfallcraft.core;

import br.com.urso.loginfallcraft.data.ConfigFile;
import br.com.urso.loginfallcraft.database.ConnectionFactory;
import br.com.urso.loginfallcraft.database.TableCreator;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class LoginFallCraft extends Plugin {
    public static LoginFallCraft pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;

        ConfigFile.setupConfigFile();

        if (loadDataBase()) {
            ChatComponent.consoleSendMessage("&aLoginFallCraft carregado com sucesso!");
            ChatComponent.consoleSendMessage("&3LoginFallCraft: Criado por " + getDescription().getAuthor());
        } else {
            this.onDisable();
        }
    }

    @Override
    public void onDisable() {

    }


    private boolean loadDataBase() {
        String database = ConfigFile.getConfigFile().getString("database.type").toLowerCase();

        if (!(database.equals("mysql") || database.equals("sqlite"))) {
            ChatComponent.consoleSendMessage("&cErro ao conectar com o banco de dados, verifique o arquivo config.cfg");
            ChatComponent.consoleSendMessage("&cDesabilitando plugin!");
            return false;
        }

        ConnectionFactory.databaseType = database;
        new TableCreator();
        return true;
    }
}
