package br.com.urso.loginfallcraft.utils;

import br.com.urso.loginfallcraft.core.LoginFallCraft;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class ChatComponent {
    public static String format(String m) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', m);
    }

    public static void sendMessage(String string) {
        LoginFallCraft.pluginInstance.getProxy().getConsole().sendMessage(new ComponentBuilder(ChatComponent.format(string)).create());
    }
}
