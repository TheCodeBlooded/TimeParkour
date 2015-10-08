package me.codeblooded.timeparkour.plugin.game.parkour;

import org.bukkit.Location;

import java.util.HashMap;

public class Parkour {

    private String name;

    private Location start, end;

    private boolean initialized = false;

    private HashMap<Integer, Location> checkpoints = new HashMap<Integer, Location>();

    public Parkour(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public HashMap<Integer, Location> getCheckpoints() {
        return checkpoints;
    }

    public void initialize() {
        if(name != null && start != null && end != null)
            initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void destroy() {
        this.name = null;

        this.start = null;

        this.end = null;

        this.checkpoints = null;
    }
}
