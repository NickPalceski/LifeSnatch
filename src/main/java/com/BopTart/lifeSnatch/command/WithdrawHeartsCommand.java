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
            sender.sendMessage("§cSolo i giocatori possono usare questo comando!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("§cUso corretto: /withdrawhearts <quantità>");
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cDevi inserire un numero valido!");
            return true;
        }

        if (amount <= 0) {
            player.sendMessage("§cLa quantità deve essere maggiore di zero!");
            return true;
        }

        int currentHearts = heartsManager.getHearts(player);

        if (currentHearts <= amount) {
            player.sendMessage("§cNon hai abbastanza cuori da ritirare!");
            return true;
        }

        // Aggiorna i cuori del player
        heartsManager.setHearts(player, currentHearts - amount);

        // Crea e dà i cuori speciali
        ItemStack heartItem = Heart.createHeartItem();
        heartItem.setAmount(amount);
        player.getInventory().addItem(heartItem);

        player.sendMessage("§ayou have got §c" + amount + " §ahearts/i!");
        return true;
    }
}
