package me.codeblooded.timeparkour.plugin.command.commands;

import me.codeblooded.timeparkour.TimeParkour;
import me.codeblooded.timeparkour.plugin.command.Command;
import me.codeblooded.timeparkour.plugin.game.parkour.Parkour;
import me.codeblooded.timeparkour.plugin.utils.Message;
import org.bukkit.entity.Player;

public class AddCheckpoint extends Command {

    public AddCheckpoint(String name, String help, String[] args) {
        super(name, help, args);
    }

    @Override
    public void run(Player p, String[] args) {
        if(args.length == 0) {
            p.sendMessage(Message.ERROR.format("&c/timeparkour addcheckpoint <map>"));

            return;
        }

        if(TimeParkour.getParkourManager().getParkour(args[0]) != null) {
            p.sendMessage(Message.ERROR.format("&cNo map found with the name " + args[0] + "."));

            return;
        }

        int id = TimeParkour.getParkourManager().getParkour(args[0]).getCheckpoints().size() + 1;

        TimeParkour.getParkourManager().setMapCheckpoint(args[0], p.getLocation(), id);

        p.sendMessage(Message.SUCCESS.format("&eMap checkpoint successfully set at your current location with the ID: " + id + "."));
    }
}
