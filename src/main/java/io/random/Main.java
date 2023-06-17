package io.random;

import io.random.Command.MainCommand;
import io.random.Listener.CloseEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public void command() {
        Bukkit.getPluginCommand("random").setExecutor(new MainCommand());
    }

    public void listener() {
        Bukkit.getPluginManager().registerEvents(new CloseEvent(), this);
    }

    @Override
    public void onEnable() {
        plugin = this;
        saveConfig();
        command();
        listener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }
}
