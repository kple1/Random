package io.random.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static io.random.Main.plugin;

public class Registration implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();
            String lore = item.getLore().toString().replace("[", "").replace("]", "");
            plugin.getConfig().set("name", item);
            plugin.getConfig().set("lore", lore);
            plugin.saveConfig();
        }
        return false;
    }
}
