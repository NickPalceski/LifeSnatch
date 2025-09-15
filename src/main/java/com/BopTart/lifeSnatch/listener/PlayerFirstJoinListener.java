package com.BopTart.lifeSnatch.listener;

import com.BopTart.lifeSnatch.database.LifeSnatchDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerFirstJoinListener implements Listener {
    private final LifeSnatchDatabase db = LifeSnatchDatabase.getDatabase();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(!(p.hasPlayedBefore())){
            try {
                db.addPlayer(p);
            } catch (SQLException ex) {
                System.out.println("Could not add player to database! " + ex.getMessage());
            }
        }
    }
}
