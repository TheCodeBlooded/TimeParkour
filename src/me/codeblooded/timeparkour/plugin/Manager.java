package me.codeblooded.timeparkour.plugin;

import me.codeblooded.timeparkour.plugin.utils.Configuration;
import me.codeblooded.timeparkour.plugin.utils.DBConnector;
import org.bukkit.plugin.Plugin;

public class Manager {

    private Plugin plugin;

    private Configuration maps;

    private DBConnector connector;

    public Manager(Plugin plugin) {
        this.plugin = plugin;

        this.maps = new Configuration("maps.yml", plugin);

        this.connector = new DBConnector("", "", "", "");
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Configuration getMaps() {
        return maps;
    }

    public DBConnector getConnector() {
        return connector;
    }
}
