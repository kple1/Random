package io.random.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class ListRandom implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            for (int i = 0; i < 100; i++) {
                String get = plugin.getConfig().getString("가챠목록." + i + ".name");

                if (get == null) {
                    continue;
                }

                player.sendMessage(get);
            }
        }
        return false;
    }
}
