package br.com.urso.loginfallcraft.events;

import br.com.urso.loginfallcraft.core.LoginFallCraftBungee;
import br.com.urso.loginfallcraft.data.PluginBungeeData;
import br.com.urso.loginfallcraft.database.AccountDAO;
import br.com.urso.loginfallcraft.entitys.GamePlayer;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.CommunicationChannel;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        PluginBungeeData.getGamePlayerMap().put(event.getPlayer(), new GamePlayer(event.getPlayer().getSocketAddress()));
        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.isPlayer(event.getPlayer().getUniqueId().toString())) {
            ChatComponent.playerSendMessage(MESSAGES.NEED_LOGIN, event.getPlayer());
            Title title = LoginFallCraftBungee.pluginInstance.getProxy().createTitle();
            title.title(ChatComponent.playerSendMessage(MESSAGES.WELCOME_LOGIN_MESSAGE));
            event.getPlayer().sendTitle(title);
        } else {
            ChatComponent.playerSendMessage(MESSAGES.NEED_REGISTER, event.getPlayer());
            Title title = LoginFallCraftBungee.pluginInstance.getProxy().createTitle();
            title.title(ChatComponent.playerSendMessage(MESSAGES.WELCOME_REGISTER_MESSAGE));
            event.getPlayer().sendTitle(title);
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        PluginBungeeData.getGamePlayerMap().remove(event.getPlayer());
        CommunicationChannel.loginAction(event.getPlayer(), event.getPlayer().getUniqueId().toString(), "lock");
    }

    @EventHandler
    public void onPlayerSwitchServer(ServerSwitchEvent event) {
        if (PluginBungeeData.getGamePlayerMap().containsKey(event.getPlayer())) {
            if (PluginBungeeData.getGamePlayerMap().get(event.getPlayer()).isLogged()) {
                CommunicationChannel.loginAction(event.getPlayer(), event.getPlayer().getUniqueId().toString(), "unlock");
            } else {
                CommunicationChannel.loginAction(event.getPlayer(), event.getPlayer().getUniqueId().toString(), "lock");
            }
        } else {
            PluginBungeeData.getGamePlayerMap().put(event.getPlayer(), new GamePlayer(event.getPlayer().getSocketAddress()));
            CommunicationChannel.loginAction(event.getPlayer(), event.getPlayer().getUniqueId().toString(), "lock");
        }
    }
}
