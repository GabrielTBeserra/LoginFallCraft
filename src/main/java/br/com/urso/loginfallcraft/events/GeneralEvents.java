package br.com.urso.loginfallcraft.events;

import br.com.urso.loginfallcraft.data.PluginData;
import br.com.urso.loginfallcraft.entitys.GamePlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        PluginData.getGamePlayerMap().put(event.getPlayer(), new GamePlayer(event.getPlayer().getSocketAddress()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerDisconnectEvent event) {
        PluginData.getGamePlayerMap().remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerSwitchServer(ServerSwitchEvent event) {
        if (!PluginData.getGamePlayerMap().containsKey(event.getPlayer())) {
            if (!PluginData.getGamePlayerMap().get(event.getPlayer()).isLogged()) {

            }
        }
    }
}
