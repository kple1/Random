package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class CreateRandom implements CommandExecutor {

    String title = Color.chat("&f[ &b가챠 &f] ");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 2) {
                if (player.isOp()) {
                    if (plugin.getConfig().getConfigurationSection("가챠목록") != null) {
                        int nextAvailableIndex = getNextAvailableIndex();
                        plugin.getConfig().set("가챠목록." + nextAvailableIndex + ".name", args[1]);
                    } else {
                        plugin.getConfig().set("가챠목록." + 0 + ".name", args[1]);
                    }
                    plugin.saveConfig();
                    player.sendMessage(Color.chat(title + "&c" + args[1] + "&f을(를) 만드셨습니다."));
                }
            }
        }
        return false;
    }

    private int getNextAvailableIndex() {
        int nextAvailableIndex = 0;
        while (plugin.getConfig().getString("가챠목록." + nextAvailableIndex) != null) {
            nextAvailableIndex++;
        }
        return nextAvailableIndex;
    }
}


