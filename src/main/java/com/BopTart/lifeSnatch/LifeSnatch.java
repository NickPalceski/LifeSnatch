package com.BopTart.lifeSnatch;

import com.BopTart.lifeSnatch.command.SetHeartsCommand;
import com.BopTart.lifeSnatch.command.ViewHeartsCommand;
import com.BopTart.lifeSnatch.command.WithdrawHeartsCommand;
import com.BopTart.lifeSnatch.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeSnatch extends JavaPlugin {

    private static LifeSnatch instance;


    @Override
    public void onEnable() {
        instance = this;
        ConfigManager configManager = new ConfigManager();
        configManager.loadConfig();

        getCommand("sethearts").setExecutor(new SetHeartsCommand());
        getCommand("viewhearts").setExecutor(new ViewHeartsCommand());
        getCommand("withdrawhearts").setExecutor(new WithdrawHeartsCommand());
    }

    @Override
    public void onDisable() {
    }







    public static LifeSnatch getInstance() {
        return instance;
    }
}
