package io.random.Listener;

import io.random.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

import static io.random.Main.plugin;

public class CloseEvent implements Listener {

    private Inventory inv;
    String title = Color.chat("&f[ &b가챠 &f] ");

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        ConfigurationSection gameName = plugin.getConfig().getConfigurationSection("가챠목록");

        if (gameName == null) return;

        for (String key : gameName.getKeys(false)) {
            String get = plugin.getConfig().getString("가챠목록." + key + ".name");
            if (event.getView().getTitle().equals(get)) {

                this.inv = Bukkit.createInventory(null, 54, get);

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
                break;
            }
        }
        plugin.saveConfig();
    }

    @EventHandler
    public void closeEvent(InventoryCloseEvent event) {
        for (int j = 0; j <= 100; j++) {
            String getPlugin = plugin.getConfig().getString("가챠목록." + j + ".name");
            if (event.getView().getTitle().equals(getPlugin + " 공지설정")) {
                inv = event.getInventory();
                for (int i = 0; i < 54; i++) {

                    ItemStack item = inv.getItem(i);
                    if (item != null) {
                        ItemMeta itemMeta = item.getItemMeta();
                        String itemName = itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : item.getType().toString();
                        plugin.getConfig().set(getPlugin + " 공지설정." + i + ".NoticeSet", item);
                        plugin.getConfig().set(getPlugin + " 공지설정." + i + ".itemName", itemName);
                    } else {
                        plugin.getConfig().set(getPlugin + " 공지설정." + i + ".NoticeSet", null);
                        plugin.getConfig().set(getPlugin + " 공지설정." + i + ".itemName", null);
                    }
                }
            }
        }
        plugin.saveConfig();
    }
}
