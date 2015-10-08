package me.codeblooded.timeparkour.plugin.game;

import me.codeblooded.timeparkour.TimeParkour;
import me.codeblooded.timeparkour.plugin.game.parkour.Parkour;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Game {

    private UUID player;

    /*
     *   Parkour start and end time
     *   Used to calculate total time
     */
    private Long start, end;

    private String map;

    private Integer checkpointId = 0;

    /*
     *   Players inventory before the map
     *   Used to restore inventory after map
     */
    protected ItemStack[] armour, items;

    public Game(UUID player, String map) {
        this.player = player;

        this.map = map;
    }

    public UUID getPlayer() {
        return player;
    }

    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }

    public String getMap() {
        return map;
    }

    public int getCheckpointId() {
        return checkpointId;
    }

    /*
     *   Teleports the player to the map
     *   Backups the players inventory
     *   Gives the player parkour items
     *   Sets start time
     */
    public void start() {
        Player player = Bukkit.getPlayer(this.player);

        Parkour parkour = TimeParkour.getParkourManager().getParkour(this.map);

        player.teleport(parkour.getStart());

        this.armour = player.getInventory().getArmorContents();

        this.items = player.getInventory().getContents();

        player.getInventory().clear();

        // TODO Give items

        player.updateInventory();

        this.start = System.currentTimeMillis();
    }

    /*
     *   Teleports the player back to the lobby
     *   Restores players inventory
     *   Calculates and logs time
     *   Destroys game
     */
    public void end() {
        Player player = Bukkit.getPlayer(this.player);

        player.teleport(TimeParkour.getParkourManager().getLobby());

        player.getInventory().setArmorContents(this.armour);

        player.getInventory().setContents(this.items);

        player.updateInventory();

        this.end = System.currentTimeMillis();

        // TODO Log times

        destroy();
    }

    /*
     *   Teleports the player back to the lobby
     *   Restores players inventory
     *   Destroys game
     */
    public void forceEnd() {
        Player player = Bukkit.getPlayer(this.player);

        player.teleport(TimeParkour.getParkourManager().getLobby());

        player.getInventory().setArmorContents(this.armour);

        player.getInventory().setContents(this.items);

        player.updateInventory();

        destroy();
    }

    public void teleportToLastCheckpoint() {
        Bukkit.getPlayer(this.player).teleport(TimeParkour.getParkourManager().getParkour(this.map).getCheckpoints().get(this.checkpointId));
    }

    /*
     *   TimeParkour
     *
     *   Time: %time%
     *
     *   Checkpoint: %checkpoint%
     *
     *   Map: %map%
     *
     *   Record: %record%
     *
     *   ------------------
     */
    public void createScoreboard() {

    }

    public void destroy() {
        this.player = null;

        this.start = null;

        this.end = null;

        this.map = null;

        this.checkpointId = null;
    }
}
