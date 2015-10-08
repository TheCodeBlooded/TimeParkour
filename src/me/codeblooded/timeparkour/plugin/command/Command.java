package me.codeblooded.timeparkour.plugin.command;

import org.bukkit.entity.Player;

public abstract class Command {

    private String name, help;

    private String[] args;

    public Command(String name, String help, String[] args) {
        this.name = name;

        this.help = help;

        this.args = args;
    }

    public String getName() {
        return name;
    }

    public String getHelp() {
        return help;
    }

    public String[] getArgs() {
        return args;
    }

    public abstract void run(Player p, String[] args);
}
