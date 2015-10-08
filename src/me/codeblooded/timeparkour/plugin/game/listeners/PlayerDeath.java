package me.codeblooded.timeparkour.plugin.game.listeners;

import me.codeblooded.timeparkour.TimeParkour;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerDeath implements Listener {

    protected Plugin plugin;

    public PlayerDeath(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if(!(e.getEntity() instanceof Player))
            return;

        Player p = (Player) e.getEntity();

        if(TimeParkour.getGameManager().getGames().containsKey(p.getUniqueId())) {
            e.getDrops().clear();

            e.setDeathMessage(null);

            e.setDroppedExp(0);
        }
    }
}
