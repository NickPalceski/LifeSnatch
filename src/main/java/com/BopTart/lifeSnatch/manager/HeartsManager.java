package com.BopTart.lifeSnatch.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HeartsManager {

    public static double getPlayerHearts(String targetName){
        Player target = Bukkit.getPlayerExact(targetName);

        try{
            if(target != null){
                return target.getHealth() / 2;
            } else {
                return -1;
            }
        } catch (Exception e){
            return -1;
        }
    }
}
