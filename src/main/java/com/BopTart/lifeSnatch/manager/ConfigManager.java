package com.BopTart.lifeSnatch.manager;

import com.BopTart.lifeSnatch.LifeSnatch;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private static final LifeSnatch plugin = LifeSnatch.getInstance();
    private static ConfigManager configManager;
    private static FileConfiguration config;

    private static double maxHearts;
    private static double heartGainLossAmount;
    private static double startingHearts;

    public ConfigManager() {
        config = plugin.getConfig();
        plugin.saveDefaultConfig();
        configManager = this;
    }

    public void loadConfig() {
        maxHearts = config.getDouble("maxHearts");
        heartGainLossAmount = config.getDouble("heartGainLossAmount");
        startingHearts = config.getDouble("startingHearts");
    }







    public static ConfigManager getConfigManager() {
        return configManager;
    }
    public static double getMaxHearts() {
        return maxHearts;
    }
    public static double getHeartGainLossAmount() {
        return heartGainLossAmount;
    }

    public static double getStartingHearts() {
        return startingHearts;
    }

}
