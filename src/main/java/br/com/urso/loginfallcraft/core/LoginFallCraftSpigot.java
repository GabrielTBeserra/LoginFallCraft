package br.com.urso.loginfallcraft.core;

import br.com.urso.loginfallcraft.data.PluginSpigotData;
import br.com.urso.loginfallcraft.utils.EventRemover;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.UUID;

public class LoginFallCraftSpigot extends JavaPlugin implements PluginMessageListener, Listener {


    @Override
    public void onEnable() {
       PluginSpigotData.loggedPlayers = new ArrayList<>();
        new EventRemover(this);
        getServer().getConsoleSender().sendMessage(formatter("&aLoginFallCraft carregado com sucesso!"));
        getServer().getConsoleSender().sendMessage(formatter("&3LoginFallCraft: Criado por 1Urso (1Urso#8730)"));
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getMessenger().registerIncomingPluginChannel(this, "flogin", this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase("flogin")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subChannel = in.readUTF();
        if (subChannel.equalsIgnoreCase("uuid")) {


            String data1 = in.readUTF();
            String data2 = in.readUTF();


            if (data1.equals("unlock")) {
                Player p = Bukkit.getPlayer(UUID.fromString(data2));
                PluginSpigotData.loggedPlayers.add(p);
            } else if (data1.equals("lock")) {
                Player p = Bukkit.getPlayer(UUID.fromString(data2));
                PluginSpigotData.loggedPlayers.remove(p);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if(PluginSpigotData.loggedPlayers.contains(event.getPlayer())){
            PluginSpigotData.loggedPlayers.remove(event.getPlayer());
        }
    }

    private String formatter(String s){
        return ChatColor.translateAlternateColorCodes('&' , s);
    }
}
