package io.random.Listener;

import io.random.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static io.random.Main.plugin;

public class CloseEvent implements Listener {

    private Inventory inv;
    String title = Color.chat("&f[ &b가챠 &f] ");

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        for (int i = 0; i < 100; i++) {
            String get = plugin.getConfig().getString("가챠목록." + i + ".name");
            if (get == null) {
                continue;
            }

            this.inv = Bukkit.createInventory(null, 54, get);

            if (event.getView().getTitle().equals(get)) {
                inv = event.getInventory();
                for (int j = 0; j < 54; j++) {
                    ItemStack item = inv.getItem(j);
                    if (item != null) {
                        plugin.getConfig().set(get + "." + j + ".item", item);
                        plugin.getConfig().set(get + "." + j + ".slot", j);
                    } else {
                        plugin.getConfig().set(get + "." + j + ".item", null);
                        plugin.getConfig().set(get + "." + j + ".slot", null);
                    }
                }
                player.sendMessage(title + "설정이 저장되었습니다.");
            }
        }
        plugin.saveConfig();
    }
}
