package me.codeblooded.timeparkour.plugin.game.listeners;

import me.codeblooded.timeparkour.TimeParkour;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

public class PlayerRespawn implements Listener {

    protected Plugin plugin;

    public PlayerRespawn(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if(TimeParkour.getGameManager().getGames().containsKey(p.getUniqueId())) {
            e.setRespawnLocation(TimeParkour.getParkourManager().getParkour(TimeParkour.getGameManager().getGames().get(p.getUniqueId()).getMap()).getCheckpoints().get(TimeParkour.getGameManager().getGames().get(p.getUniqueId()).getCheckpointId()));

            // TODO Give game items
        }
    }
}
