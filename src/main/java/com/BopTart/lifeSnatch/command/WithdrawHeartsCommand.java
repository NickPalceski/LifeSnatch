package com.BopTart.lifeSnatch.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WithdrawHeartsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        double playerHearts = (player.getHealth() / 2.0) - 1.0; // Convert health to hearts and subtract 1 heart to prevent death
        if (args[0] != null) {
            try {
                double heartsToWithdraw = Double.parseDouble(args[0]);
                if (heartsToWithdraw <= 0) {
                    player.sendMessage("You must withdraw a positive number of hearts.");
                    return true;
                }
                if (heartsToWithdraw > playerHearts) {
                    player.sendMessage("You do not have enough hearts to withdraw that amount.");
                    return true;
                }
                player.setHealth(player.getHealth() - (heartsToWithdraw * 2.0));
                player.sendMessage("You have withdrawn " + heartsToWithdraw + " hearts.");
                //TODO: give player heart item
            } catch (NumberFormatException e) {
                player.sendMessage("Invalid number format. Please enter a valid number of hearts to withdraw.");
            }
        } else {
            player.sendMessage("Usage: /withdrawhearts <amount>");
        }

        return true;
    }
}
