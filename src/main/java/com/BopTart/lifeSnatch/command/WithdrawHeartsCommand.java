package com.BopTart.lifeSnatch.command;

import com.BopTart.lifeSnatch.item.Heart;
import com.BopTart.lifeSnatch.manager.HeartsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WithdrawHeartsCommand implements CommandExecutor {

    private final HeartsManager heartsManager;

    public WithdrawHeartsCommand(HeartsManager heartsManager) {
        this.heartsManager = heartsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("§cUsage: /withdrawhearts <amount>");
            return true;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cYou must enter a valid number!");
            return true;
        }

        if (amount <= 0) {
            player.sendMessage("§cThe amount must be greater than zero!");
            return true;
        }

        double currentHearts = heartsManager.getHearts(player);

        if (currentHearts <= amount) {
            player.sendMessage("§cYou do not have enough hearts to withdraw!");
            return true;
        }

        // Update player hearts
        heartsManager.setHearts(player, currentHearts - amount);

        // Give heart item(s) — since ItemStack amount must be int, round it
        ItemStack heartItem = Heart.createHeartItem();
        heartItem.setAmount((int) Math.floor(amount)); // floors to nearest int
        player.getInventory().addItem(heartItem);

        player.sendMessage("§aYou have withdrawn §c" + amount + " §aheart(s)!");
        return true;
    }
}