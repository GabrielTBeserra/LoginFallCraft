package br.com.urso.loginfallcraft.core;

import br.com.urso.loginfallcraft.commands.admins.Reload;
import br.com.urso.loginfallcraft.commands.admins.Unregister;
import br.com.urso.loginfallcraft.commands.players.ChangePassword;
import br.com.urso.loginfallcraft.commands.players.Login;
import br.com.urso.loginfallcraft.commands.players.Register;
import br.com.urso.loginfallcraft.data.ConfigFile;
import br.com.urso.loginfallcraft.database.ConnectionFactory;
import br.com.urso.loginfallcraft.database.TableCreator;
import br.com.urso.loginfallcraft.events.GeneralEvents;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.MESSAGES;
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
            loadEvents();
            loadCommands();
            MESSAGES.loadMessages();
        } else {
            this.onDisable();
        }
    }

    @Override
    public void onDisable() {
        ChatComponent.consoleSendMessage("&cLoginFallCraft descarregado com sucesso!");
    }

    private void loadCommands() {
        getProxy().getPluginManager().registerCommand(this, new Login("login"));
        getProxy().getPluginManager().registerCommand(this, new Register("register"));
        getProxy().getPluginManager().registerCommand(this, new ChangePassword("changepassword"));
        getProxy().getPluginManager().registerCommand(this, new Unregister("unregister", "flogin.unregister", "uregister"));
        getProxy().getPluginManager().registerCommand(this, new Reload("reloadlogin", "flogin.reload", "loginreload", "lreload"));
    }

    private void loadEvents() {
        getProxy().getPluginManager().registerListener(this, new GeneralEvents());
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
