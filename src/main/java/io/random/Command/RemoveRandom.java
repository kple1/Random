package io.random.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.random.Main.plugin;

public class RemoveRandom implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                for (int i = 0; i < 100; i++) {
                    String get = plugin.getConfig().getString("가챠목록." + i + ".name");
                    if (Objects.equals(get, args[1])) {
                        plugin.getConfig().set("가챠목록." + i + ".name", null);
                        plugin.getConfig().set(args[1] + "." + i + ".item", null);
                        plugin.getConfig().set(args[1] + "." + i + ".slot", null);
                        plugin.getConfig().set(args[1] + "." + i + ".ran", null);
                        plugin.saveConfig();
                        player.sendMessage(args[1] + "이 제거 되었습니다.");
                    }
                }
            }
        }
        return false;
    }
}

