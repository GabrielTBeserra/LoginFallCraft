package br.com.urso.loginfallcraft.utils;

import br.com.urso.loginfallcraft.core.LoginFallCraftSpigot;
import br.com.urso.loginfallcraft.data.PluginSpigotData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public class EventRemover implements Listener {
    public EventRemover(LoginFallCraftSpigot plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        this.defaultEventAction(event);
        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        this.defaultEventAction(event);
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] message = event.getMessage().split(" ");
        if (message[0].equalsIgnoreCase("/login") || message[0].equalsIgnoreCase("/register")) {
            return;
        }
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        this.defaultEventAction(event);
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDrink(PlayerItemConsumeEvent event) {
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        this.defaultEventAction(event);
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        Location from = event.getFrom();
        Location to = event.getTo();
        if (from.getX() != to.getX() || from.getZ() != to.getZ() || from.getY() != to.getY()) {
            event.setTo(from);
        }

    }

    @EventHandler
    public void mandaBola(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }


        e.setCancelled(true);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();

        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player p = (Player) event.getPlayer();
        if (PluginSpigotData.loggedPlayers.contains(p)) {
            return;
        }
        p.closeInventory();
        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();


        for (Player target: Bukkit.getOnlinePlayers()) {
            if(target.getAddress().getHostName().equals(player.getAddress().getHostName()) && !player.equals(target)){
                player.kickPlayer(ChatComponent.format("&4Voce ja esta logado com esse IP no servidor"));
            }
        }



    }

    private void defaultEventAction(PlayerEvent event) {
        if (!(event instanceof Cancellable)) {
            throw new IllegalArgumentException("Event cannot be cancelled!");
        }
        final Player player = event.getPlayer();
        ((Cancellable) event).setCancelled(true);
    }
}
