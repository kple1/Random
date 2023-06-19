package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class CheckReward implements CommandExecutor {

    String title = Color.chat("&f[ &b가챠 &f] ");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            plugin.getConfig().set("책개수", Integer.parseInt(args[1]));
            plugin.saveConfig();
            player.sendMessage(title + "책 개수가 설정되었습니다.");
        }
        return false;
    }
}
