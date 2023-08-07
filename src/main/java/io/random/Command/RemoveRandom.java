package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.random.Main.plugin;

public class RemoveRandom implements CommandExecutor {

    String title = Color.chat("&f[ &b가챠 &f] ");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player) || !player.isOp()) {
            return true;
        }

        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("RightClick");
        ConfigurationSection gameList = plugin.getConfig().getConfigurationSection("가챠목록");
        if (configSection == null || gameList == null) {
            return true;
        }

        boolean found = false;
        for (String gachaList : gameList.getKeys(false)) {
            String getGachaList = gameList.getString(gachaList + ".name");
            if (args[1].equals(getGachaList)) {
                plugin.getConfig().set("가챠목록." + gachaList, null);
                plugin.getConfig().set(args[1], null);
                plugin.saveConfig();
                found = true;
            }
        }

        for (String rightClick : configSection.getKeys(false)) {
            String getName = configSection.getString(rightClick + ".name");
            if (args[1].equals(getName)) {
                plugin.getConfig().set("RightClick." + rightClick, null);
                plugin.saveConfig();
                found = true;
            }
        }

        if (found) {
            plugin.saveConfig();
            player.sendMessage(title + args[1] + "이 제거 되었습니다.");
        }
        return false;
    }
}

