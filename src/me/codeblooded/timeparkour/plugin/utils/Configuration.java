package me.codeblooded.timeparkour.plugin.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Configuration {
	
	private FileConfiguration config;
	
	private File file;
	
	protected Plugin pl;
	
	public Configuration(String configName, Plugin pl) {
		if (!pl.getDataFolder().exists()) {
			pl.getDataFolder().mkdir();
		}
		
		file = new File(pl.getDataFolder(), configName + ".yml");

		try {
			FileUtils.createFile(file, configName + ".yml", pl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		config = new YamlConfiguration();
		
		try {
			FileUtils.loadYamls(config, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void save() {
		try {
			this.config.save(file);
		} catch (Exception e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save " + file.getName() + ".yml");
		}
	}
	
	public void reload() {
		config = new YamlConfiguration();
		
		try {
			FileUtils.loadYamls(config, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
