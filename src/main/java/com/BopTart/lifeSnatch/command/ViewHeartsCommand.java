package com.BopTart.lifeSnatch.command;

import com.BopTart.lifeSnatch.manager.HeartsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewHeartsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (args[0] == null || !(player.hasPermission("lifesnatch.viewhearts"))) {
            player.sendMessage("usage: /viewhearts <player>");
            return false;
        }

        String targetPlayerName = args[0];
        double targetHealth = HeartsManager.getPlayerHearts(targetPlayerName);

        player.sendMessage(targetPlayerName + " has " + targetHealth + " hearts.");
        return true;

    }
}
