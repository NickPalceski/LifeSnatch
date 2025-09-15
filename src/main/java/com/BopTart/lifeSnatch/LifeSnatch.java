package com.BopTart.lifeSnatch;

import com.BopTart.lifeSnatch.command.SetHeartsCommand;
import com.BopTart.lifeSnatch.command.ViewHeartsCommand;
import com.BopTart.lifeSnatch.command.WithdrawHeartsCommand;
import com.BopTart.lifeSnatch.database.LifeSnatchDatabase;
import com.BopTart.lifeSnatch.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LifeSnatch extends JavaPlugin {

    private static LifeSnatch instance;
    private LifeSnatchDatabase db;


    @Override
    public void onEnable() {
        instance = this;
        ConfigManager configManager = new ConfigManager();
        configManager.loadConfig();

        try {
            db = new LifeSnatchDatabase(getDataFolder().getAbsolutePath()+ "/lifesnatch.db");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not connect to database! disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }

        getCommand("sethearts").setExecutor(new SetHeartsCommand());
        getCommand("viewhearts").setExecutor(new ViewHeartsCommand());
        getCommand("withdrawhearts").setExecutor(new WithdrawHeartsCommand());
    }

    @Override
    public void onDisable() {
        try{
            db.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }







    public static LifeSnatch getInstance() {
        return instance;
    }
}
