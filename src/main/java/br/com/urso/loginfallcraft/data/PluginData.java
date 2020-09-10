package br.com.urso.loginfallcraft.data;

import br.com.urso.loginfallcraft.entitys.GamePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginData {
    private static final List<ProxiedPlayer> loggedPlayers = new ArrayList<ProxiedPlayer>();
    private static final Map<ProxiedPlayer, GamePlayer> gamePlayerMap = new HashMap<ProxiedPlayer, GamePlayer>();


    public static List<ProxiedPlayer> getLoggedPlayers() {
        return loggedPlayers;
    }

    public static Map<ProxiedPlayer, GamePlayer> getGamePlayerMap() {
        return gamePlayerMap;
    }
}
