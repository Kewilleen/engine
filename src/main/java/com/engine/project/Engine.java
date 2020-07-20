package com.engine.project;

import org.bukkit.plugin.java.JavaPlugin;

public class Engine extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getLogger().info("Plugin enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled successfully!");
    }

}