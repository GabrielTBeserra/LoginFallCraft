package br.com.urso.loginfallcraft.data;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class PluginData {
    private static List<ProxiedPlayer> loggedPlayers = new ArrayList<ProxiedPlayer>();

    public static List<ProxiedPlayer> getLoggedPlayers() {

        return loggedPlayers;
    }
}
