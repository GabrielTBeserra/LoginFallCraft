package br.com.urso.loginfallcraft.events;

import br.com.urso.loginfallcraft.data.PluginData;
import br.com.urso.loginfallcraft.database.AccountDAO;
import br.com.urso.loginfallcraft.entitys.GamePlayer;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        PluginData.getGamePlayerMap().put(event.getPlayer(), new GamePlayer(event.getPlayer().getSocketAddress()));
        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.isPlayer(event.getPlayer().getUniqueId().toString())) {
            ChatComponent.playerSendMessage(MESSAGES.NEED_LOGIN, event.getPlayer());
        } else {
            ChatComponent.playerSendMessage(MESSAGES.NEED_REGISTER, event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        PluginData.getGamePlayerMap().remove(event.getPlayer());
    }
/*
    @EventHandler
    public void onPlayerSwitchServer(ServerSwitchEvent event) {
        if (!PluginData.getGamePlayerMap().containsKey(event.getPlayer())) {
            PluginData.getGamePlayerMap().put(event.getPlayer(), new GamePlayer(event.getPlayer().getSocketAddress()));
            if (!PluginData.getGamePlayerMap().get(event.getPlayer()).isLogged()) {
                ChatComponent.playerSendMessage(MESSAGES.NEED_LOGIN, event.getPlayer());
            }
        } else {
            if (!PluginData.getGamePlayerMap().get(event.getPlayer()).isLogged()) {
                ChatComponent.playerSendMessage(MESSAGES.NEED_LOGIN, event.getPlayer());
            }
        }
    }*/
}
