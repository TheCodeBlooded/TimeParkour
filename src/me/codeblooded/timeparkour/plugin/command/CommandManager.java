package me.codeblooded.timeparkour.plugin.command;

import me.codeblooded.timeparkour.plugin.command.commands.*;
import me.codeblooded.timeparkour.plugin.utils.Message;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    protected me.codeblooded.timeparkour.plugin.command.Command[] commands;

    public CommandManager() {
        this.commands = new me.codeblooded.timeparkour.plugin.command.Command[7];

        this.commands[0] = new AddCheckpoint("AddCheckpoint", "<help>", new String[] {"<args>"});

        this.commands[1] = new Create("Create", "<help>", new String[] {"<args>"});

        this.commands[2] = new Remove("Remove", "<help>", new String[] {"<args>"});

        this.commands[3] = new SetEnd("SetEnd", "<help>", new String[] {"<args>"});

        this.commands[4] = new SetStart("SetStart", "<help>", new String[] {"<args>"});

        this.commands[5] = new Stats("Stats", "<help>", new String[] {"<args>"});

        this.commands[6] = new Top("Top", "<help>", new String[] {"<args>"});
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("timeparkour")) {
            if(args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[&8====================&7[&4Commands&7]&8====================&4] \n "));

                for(me.codeblooded.timeparkour.plugin.command.Command cmd : commands) {
                    p.sendMessage(ChatColor.RED + "  ?    " + ChatColor.YELLOW + "/infected " + cmd.getName() + " " + cmd.getArgs() + " \n        (" + cmd.getHelp() + ") \n ");
                }

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[&8==================================================&4]"));
            } else {
                me.codeblooded.timeparkour.plugin.command.Command cmd = getCommand(args[0]);

                if(cmd != null) {
                    cmd.run(p, (String[]) ArrayUtils.remove(args, 0));
                } else {
                    p.sendMessage(Message.ERROR.format(ChatColor.RED + "That is not a valid command."));
                }
            }
        }
        return false;
    }

    private me.codeblooded.timeparkour.plugin.command.Command getCommand(String cmd) {
        for(me.codeblooded.timeparkour.plugin.command.Command command : commands) {
            if(command.getName().equalsIgnoreCase(cmd)) {
                return command;
            }
        }
        return null;
    }

}
