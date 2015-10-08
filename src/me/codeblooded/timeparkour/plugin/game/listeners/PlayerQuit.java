package me.codeblooded.timeparkour.plugin.game.listeners;

import me.codeblooded.timeparkour.TimeParkour;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerQuit implements Listener {

    protected Plugin plugin;

    public PlayerQuit(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(TimeParkour.getGameManager().getGames().containsKey(p.getUniqueId())) {
            TimeParkour.getGameManager().removeGame(p);
        }
    }
}
