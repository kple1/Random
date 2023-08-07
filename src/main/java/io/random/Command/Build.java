package io.random.Command;

import io.random.Utils.Color;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static io.random.Main.plugin;

public class Build implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            PlayerInventory inventory = player.getInventory();
            ItemStack itemStack = inventory.getItemInMainHand();
            String title = Color.chat("&f[ &b가챠 &f] ");

            if (itemStack.getType() == Material.AIR) {
                return true;
            }

            if (args.length >= 2) {
                boolean found = false;
                ConfigurationSection getNumber = plugin.getConfig().getConfigurationSection("가챠목록"); // 기존 가챠목록의 개수를 가져옴
                if (getNumber == null) {
                    return true;
                }

                for (String key : getNumber.getKeys(false)) {
                    String name = plugin.getConfig().getString("가챠목록." + key + ".name");
                    if (name != null && name.equals(args[1])) {
                        found = true;
                    }
                }

                if (!found) {
                    player.sendMessage(title + "해당 이름은 존재하지 않습니다.");
                    return true;
                }

                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                String displayName = Color.chat(args[1]);
                if (plugin.getConfig().getConfigurationSection("RightClick") != null) {
                    meta.setDisplayName(Color.chat("&9&o&l" + args[1]));
                    meta.setLore(Collections.singletonList(displayName)); // 식별자를 로어에 추가

                    item.setItemMeta(meta);

                    int i = plugin.getConfig().getConfigurationSection("RightClick").getKeys(false).size(); // 기존 가챠목록의 개수를 가져옴
                    plugin.getConfig().set("RightClick." + i + ".item", itemStack);
                    plugin.getConfig().set("RightClick." + i + ".name", args[1]);
                } else {
                    meta.setDisplayName(Color.chat("&9&o&l" + args[1]));
                    meta.setLore(Collections.singletonList(displayName)); // 식별자를 로어에 추가

                    item.setItemMeta(meta);

                    plugin.getConfig().set("RightClick." + 1 + ".item", itemStack);
                    plugin.getConfig().set("RightClick." + 1 + ".name", args[1]);
                    player.sendMessage(title + "가챠가 설정되었습니다.");
                }
                plugin.saveConfig();
            }
        }
        return false;
    }
}
