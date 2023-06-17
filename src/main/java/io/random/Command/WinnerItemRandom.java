package io.random.Command;

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

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 2) {
                this.inv = Bukkit.createInventory(null, 54, args[1]);
                boolean nameExists = false;

                for (int i = 0; i < 54; i++) {
                    String name = plugin.getConfig().getString("가챠목록." + i + ".name");
                    if (name != null && name.equals(args[1])) {
                        ItemStack itemStack = plugin.getConfig().getItemStack(args[1] + "." + i + ".item");
                        inv.setItem(i, itemStack);
                        nameExists = true;
                    }
                }

                if (nameExists) {
                    player.openInventory(inv);
                } else {
                    player.sendMessage("존재하지 않는 이름입니다.");
                }
            }
        }
        return false;
    }
}
