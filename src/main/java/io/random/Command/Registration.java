package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class Registration implements CommandExecutor {

    String title = Color.chat("&f[ &b가챠 &f] ");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getAmount() == 0) {
                player.sendMessage("아이템을 들어주세요!");
                return true;
            }

            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta != null) {
                String lore = itemMeta.getLore().toString().replace("[", "").replace("]", "");

                plugin.getConfig().set("name", item);
                plugin.getConfig().set("lore", lore);
                plugin.saveConfig();
                player.sendMessage(title + "가챠가 등록되었습니다.");
            }
        }
        return false;
    }
}
