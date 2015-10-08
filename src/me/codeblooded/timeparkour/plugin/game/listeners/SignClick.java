package me.codeblooded.timeparkour.plugin.game.listeners;

import me.codeblooded.timeparkour.TimeParkour;
import me.codeblooded.timeparkour.plugin.game.parkour.Parkour;
import me.codeblooded.timeparkour.plugin.game.parkour.ParkourManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class SignClick implements Listener {

    protected Plugin plugin;

    public SignClick(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock() == null)
                return;

            if(e.getClickedBlock().getType() == Material.SIGN) {
                Sign sign = (Sign) e.getClickedBlock();

                if(ChatColor.stripColor(sign.getLine(1)) != "TimeParkour")
                    return;

                e.setCancelled(true);

                Parkour parkour = TimeParkour.getParkourManager().getParkour(ChatColor.stripColor(sign.getLine(2)));

                if(!parkour.isInitialized()) {
                    // TODO Tell the player the map is not fully setup

                    return;
                }

                TimeParkour.getGameManager().createGame(e.getPlayer(), parkour.getName());
            }
        }
    }
}
