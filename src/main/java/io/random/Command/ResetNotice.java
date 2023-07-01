package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class ResetNotice implements CommandExecutor {

    String title = Color.chat("&f[ &b가챠 &f] ");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                for (int j = 0; j < 54; j++) {
                    plugin.getConfig().set(args[1] + " 공지설정." + j + ".NoticeSet", null);
                    plugin.getConfig().set(args[1] + " 공지설정." + j + ".itemName", null);
                }
                plugin.saveConfig();
                player.sendMessage(title + args[1] + "공지설정이 초기화 되었습니다.");
            }
        }
        return false;
    }
}
