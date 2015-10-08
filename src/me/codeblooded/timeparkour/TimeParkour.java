package me.codeblooded.timeparkour;

import me.codeblooded.timeparkour.plugin.Manager;
import me.codeblooded.timeparkour.plugin.game.GameManager;
import me.codeblooded.timeparkour.plugin.game.parkour.ParkourManager;
import me.codeblooded.timeparkour.plugin.game.stats.StatsManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeParkour extends JavaPlugin {

    private static Manager manager;

    private static ParkourManager parkourManager;

    private static GameManager gameManager;

    private static StatsManager statsManager;

    @Override
    public void onEnable() {
        manager = new Manager(this);

        parkourManager = new ParkourManager();

        gameManager = new GameManager();

        statsManager = new StatsManager();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Manager getManager() {
        return manager;
    }

    public static ParkourManager getParkourManager() {
        return parkourManager;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static StatsManager getStatsManager() {
        return statsManager;
    }
}
