package io.random;

import io.random.Command.MainCommand;
import io.random.Listener.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    private Plugin citizensPlugin;

    public void command() {
        Bukkit.getPluginCommand("random").setExecutor(new MainCommand());
    }

    public void listener() {
        Bukkit.getPluginManager().registerEvents(new CloseEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WinRandom(), this);
        Bukkit.getPluginManager().registerEvents(new RightClick(), this);
        Bukkit.getPluginManager().registerEvents(new OnClickNPC(), this);
    }

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("Citizens")) {
            citizensPlugin = Bukkit.getPluginManager().getPlugin("Citizens");
            getServer().getPluginManager().registerEvents(this, this);
        } else {
            getLogger().severe("Citizens 플러그인을 찾을 수 없습니다. 플러그인이 설치되어 있는지 확인해주세요.");
            getServer().getPluginManager().disablePlugin(this);
        }
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
