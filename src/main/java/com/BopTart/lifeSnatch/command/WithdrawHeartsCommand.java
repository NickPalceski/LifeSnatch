package com.BopTart.lifeSnatch.command;

import com.BopTart.lifeSnatch.database.LifeSnatchDatabase;
import com.BopTart.lifeSnatch.item.Heart;
import com.BopTart.lifeSnatch.manager.HeartsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class WithdrawHeartsCommand implements CommandExecutor {

    private final LifeSnatchDatabase db = LifeSnatchDatabase.getDatabase();

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

        double currentHearts = 0;
        try {
            currentHearts = db.getPlayerHearts(player);
        } catch (SQLException e) {
            System.err.println("Error getting player's hearts!" + e.getMessage());
        }

        if (currentHearts <= amount) {
            player.sendMessage("§cYou do not have enough hearts to withdraw!");
            return true;
        }

        // Update player hearts
        try {
            db.updatePlayerHearts(player, currentHearts - amount);
        } catch (SQLException e) {
            System.err.println("Error updating player's hearts!" + e.getMessage());
        }

        // Give heart item(s) — since ItemStack amount must be int, round it
        ItemStack heartItem = Heart.createHeartItem();
        heartItem.setAmount((int) Math.floor(amount)); // floors to nearest int
        player.getInventory().addItem(heartItem);

        player.sendMessage("§aYou have withdrawn §c" + amount + " §aheart(s)!");
        return true;
    }
}