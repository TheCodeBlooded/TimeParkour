package me.codeblooded.timeparkour.plugin.command.commands;

import me.codeblooded.timeparkour.TimeParkour;
import me.codeblooded.timeparkour.plugin.command.Command;
import me.codeblooded.timeparkour.plugin.utils.Message;
import org.bukkit.entity.Player;

public class Stats extends Command {

    public Stats(String name, String help, String[] args) {
        super(name, help, args);
    }

    @Override
    public void run(Player p, String[] args) {
        if(args.length == 0) {
            p.sendMessage(Message.ERROR.format("&c/timeparkour stats <map>"));

            return;
        }

        if(TimeParkour.getParkourManager().getParkour(args[0]) != null) {
            p.sendMessage(Message.ERROR.format("&cNo map found with the name " + args[0] + "."));

            return;
        }


    }
}
