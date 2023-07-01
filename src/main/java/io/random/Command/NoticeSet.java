package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class NoticeSet implements CommandExecutor {

    private Inventory inv;
    String title = Color.chat("&f[ &b가챠 &f] ");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {

                for (int i = 0; i <= 100; i++) {
                    String getPlugin = plugin.getConfig().getString("가챠목록." + i);
                    if (args[1].equals(getPlugin)) {
                        return true;
                    }
                }

                this.inv = Bukkit.createInventory(null, 54, args[1]  + " 공지설정");

                for (int i = 0; i < 54; i++) {
                    ItemStack item = plugin.getConfig().getItemStack(args[1] + " 공지설정." + i + ".NoticeSet");
                    if (item == null) {
                        continue;
                    }
                    String name = plugin.getConfig().getString("reward." + i + ".itemName");
                    if (name == null) { // null 처리
                        name = "";
                    }
                    name = ChatColor.translateAlternateColorCodes('&', name);
                    // 아이템 이름 변경
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName(name);
                    item.setItemMeta(itemMeta);
                    inv.setItem(i, item);
                }
                player.sendMessage(title + "가챠 공지사항 설정 인벤토리가 오픈되었습니다");
            }
            player.openInventory(inv);
        }
        return false;
    }
}
