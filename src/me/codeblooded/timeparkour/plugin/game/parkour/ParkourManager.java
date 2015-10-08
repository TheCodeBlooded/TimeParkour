package me.codeblooded.timeparkour.plugin.game.parkour;

import me.codeblooded.timeparkour.TimeParkour;
import me.codeblooded.timeparkour.plugin.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ParkourManager {

    private static ParkourManager instance = new ParkourManager();

    private Location lobby;

    private ArrayList<Parkour> parkours = new ArrayList<Parkour>();

    public static ParkourManager getInstance() {
        return instance;
    }

    public void setup() {
        FileConfiguration fc = TimeParkour.getManager().getMaps().getConfig();

        if(fc.contains("Lobby"))
            lobby = LocationUtils.stringToLocation(fc.getString("Lobby"));

        for(String map : fc.getConfigurationSection("Maps").getKeys(false)) {
            String path = "Maps." + map;

            Parkour parkour = new Parkour(map);

            parkour.setStart(LocationUtils.stringToLocation(fc.getString(path + ".Start")));

            parkour.setEnd(LocationUtils.stringToLocation(fc.getString(path + ".End")));

            parkour.initialize();

            for(String id : fc.getConfigurationSection(path + ".Checkpoints").getKeys(false)) {
                parkour.getCheckpoints().put(Integer.valueOf(id), LocationUtils.stringToLocation(fc.getString(path + ".Checkpoints." + id)));
            }
            parkours.add(parkour);
        }
    }

    public void createMap(String name) {
        FileConfiguration fc = TimeParkour.getManager().getMaps().getConfig();

        fc.set("Maps." + name, name);

        TimeParkour.getManager().getMaps().save();

        TimeParkour.getStatsManager().createMapEntry(name);

        parkours.add(new Parkour(name));
    }

    public void setMapStart(String map, Location start) {
        if(getParkour(map) == null)
            return;

        FileConfiguration fc = TimeParkour.getManager().getMaps().getConfig();

        fc.set("Maps." + map + ".Start", LocationUtils.locationToString(start));

        TimeParkour.getManager().getMaps().save();

        getParkour(map).setStart(start);
    }

    public void setMapEnd(String map, Location end) {
        if(getParkour(map) == null)
            return;

        FileConfiguration fc = TimeParkour.getManager().getMaps().getConfig();

        fc.set("Maps." + map + ".End", LocationUtils.locationToString(end));

        TimeParkour.getManager().getMaps().save();

        getParkour(map).setEnd(end);
    }

    public void setMapCheckpoint(String map, Location checkpoint, int id) {
        if(getParkour(map) == null)
            return;

        FileConfiguration fc = TimeParkour.getManager().getMaps().getConfig();

        fc.set("Maps." + map + ".Checkpoint." + id, LocationUtils.locationToString(checkpoint));

        TimeParkour.getManager().getMaps().save();

        getParkour(map).getCheckpoints().put(id, checkpoint);
    }

    public void removeMap(String map) {
        if(getParkour(map) == null)
            return;

        FileConfiguration fc = TimeParkour.getManager().getMaps().getConfig();

        fc.set("Maps." + map, null);

        TimeParkour.getManager().getMaps().save();

        getParkour(map).destroy();

        parkours.remove(getParkour(map));
    }

    public Parkour getParkour(String name) {
        for(Parkour parkour : parkours) {
            if(parkour.getName().equalsIgnoreCase(name)) {
                return parkour;
            }
        }
        return null;
    }

    public Location getLobby() {
        return lobby;
    }

    public ArrayList<Parkour> getParkours() {
        return parkours;
    }
}
