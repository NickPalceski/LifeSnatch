package com.BopTart.lifeSnatch.listener;

import com.BopTart.lifeSnatch.LifeSnatch;
import com.BopTart.lifeSnatch.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final LifeSnatch plugin = LifeSnatch.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player deadPlayer = event.getEntity();

        if (deadPlayer.getKiller() != null) {
            Player killer = deadPlayer.getKiller();
            double killerHealth = killer.getHealth();
            double deadPlayerHealth = deadPlayer.getHealth();

            if (killerHealth == plugin.getConfig().getDouble("maxHearts")) {
                killer.sendMessage(ChatColor.RED + "You are already at the max hearts!");
                deadPlayer.sendMessage(ChatColor.RED + killer.getDisplayName() + " is already at the max hearts!");
                return;
            }

            if (deadPlayerHealth < (plugin.getConfig().getDouble("heartGainLossAmount") + 2)) {
                killer.sendMessage(ChatColor.RED + deadPlayer.getDisplayName() + " does not have enough hearts to steal from!");
                deadPlayer.sendMessage(ChatColor.RED + "You do not have enough hearts to lose!");
                return;
            }

            if ((killerHealth + (plugin.getConfig().getDouble("heartGainLossAmount"))) > plugin.getConfig().getDouble("maxHearts")) {
                killer.setHealth(plugin.getConfig().getDouble("maxHearts"));
                deadPlayer.setHealth(deadPlayerHealth - (plugin.getConfig().getDouble("heartGainLossAmount")));
                return;
            }

            killer.setHealth(killerHealth + (plugin.getConfig().getDouble("heartGainLossAmount")));
            deadPlayer.setHealth(deadPlayerHealth - (plugin.getConfig().getDouble("heartGainLossAmount")));
        }
    }
}
