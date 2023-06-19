package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class WinnerItemRandom implements CommandExecutor {

    private Inventory inv;
    String title = Color.chat("&f[ &b가챠 &f] ");

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                this.inv = Bukkit.createInventory(null, 54, args[1]);

                if (args.length >= 2) {
                    boolean found = false;
                    for (int j = 0; j < 54; j++) {
                        String name = plugin.getConfig().getString("가챠목록." + j + ".name");
                        if (name != null && name.equals(args[1])) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        player.sendMessage("해당 이름은 존재하지 않습니다.");
                        return true;
                    }

                    for (int i = 0; i < 54; i++) {
                        ItemStack item = plugin.getConfig().getItemStack(args[1] + "." + i + ".item");
                        if (item == null) {
                            continue;
                        }
                        inv.setItem(i, item);
                    }
                    player.openInventory(inv);
                    player.sendMessage(title + "당첨아이템이 설정되었습니다.");
                }
            }
        }
        return false;
    }
}