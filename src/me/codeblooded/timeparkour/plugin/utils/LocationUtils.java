package me.codeblooded.timeparkour.plugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {
	
	public static String locationToString(Location loc) {
		return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
	}
	
	public static Location stringToLocation(String location) {
		String[] loc = location.split(",");
		
		return new Location(Bukkit.getWorld(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2]), Double.parseDouble(loc[3]), Float.parseFloat(loc[4]), Float.parseFloat(loc[5]));
	}

}
