package com.BopTart.lifeSnatch.listener;

import com.BopTart.lifeSnatch.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(!(p.hasPlayedBefore())){
            p.setHealth(ConfigManager.getStartingHearts());
        }
    }
}
