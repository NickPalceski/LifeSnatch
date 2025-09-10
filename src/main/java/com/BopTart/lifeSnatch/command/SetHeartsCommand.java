package com.BopTart.lifeSnatch.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHeartsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        double newHearts;

        if(args[0] == null || args[1] == null) {
            player.sendMessage("Usage: /sethearts <player> <amount>");
            return false;
        }

        try {
            newHearts = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid number format for hearts.");
            return false;
        }

        if (!(player == null)) {
            player.setHealth(newHearts * 2);
            return true;
        }
        return true;
    }
}
