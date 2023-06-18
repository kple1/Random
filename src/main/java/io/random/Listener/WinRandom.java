package io.random.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static io.random.Main.plugin;

public class WinRandom implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        for (int i = 0; i < 54; i++) {
            String get = plugin.getConfig().getString("가챠목록." + i + ".name");
            if (event.getView().getTitle().equals(get)) {
                if (event.getSlot() >= 0 && event.getSlot() < 54) {
                    if (event.getClick().isRightClick()) {
                        player.closeInventory();
                        player.sendMessage("확률을 입력 해주세요");
                        Bukkit.getPluginManager().registerEvents(new ChatListener(player, event.getView().getTitle(), event.getSlot()), plugin);
                    }
                }
            }
        }
    }
}
