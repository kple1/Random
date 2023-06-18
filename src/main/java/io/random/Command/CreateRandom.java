package io.random.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class CreateRandom implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 2) {
                if (player.isOp()) {
                    if (plugin.getConfig().getConfigurationSection("가챠목록") != null) {
                        int i = plugin.getConfig().getConfigurationSection("가챠목록").getKeys(false).size(); // 기존 가챠목록의 개수를 가져옴
                        plugin.getConfig().set("가챠목록." + i + ".name", args[1]);
                    } else {
                        plugin.getConfig().set("가챠목록." + 0 + ".name", args[1]);
                    }
                    plugin.saveConfig();
                }
            }
        }
        return false;
    }
}


