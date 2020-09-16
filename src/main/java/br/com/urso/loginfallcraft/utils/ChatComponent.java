package br.com.urso.loginfallcraft.utils;

import br.com.urso.loginfallcraft.core.LoginFallCraftBungee;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ChatComponent {
    public static String format(String m) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', m);
    }

    public static void consoleSendMessage(String string) {
        LoginFallCraftBungee.pluginInstance.getProxy().getConsole().sendMessage(new ComponentBuilder(ChatComponent.format(string)).create());
    }

    public static BaseComponent[] playerSendMessage(String string) {
        return new ComponentBuilder(ChatComponent.format(string)).create();
    }

    public static void playerSendMessage(String string, ProxiedPlayer proxiedPlayer) {
        proxiedPlayer.sendMessage(new ComponentBuilder(ChatComponent.format(string)).create());
    }

    public static String format(String m, int i) {
        String str = m.replace("%attemps%", i + "");

        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', str);
    }


}
