package io.random.Listener;

import io.random.Utils.Color;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;

import static io.random.Main.plugin;

public class OnClickNPC implements Listener {

    @EventHandler
    public void onNPCRightClick(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();
        int itemCount = plugin.getConfig().getInt("책개수");
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Color.chat("&a&o출첵완료!"));
        item.setItemMeta(itemMeta);

        if (npc.getName().equals("출첵보상")) {
            int itemAmount = 1;
            boolean hasItem = false;

            for (ItemStack inventoryItem : player.getInventory().getContents()) {
                if (inventoryItem != null && inventoryItem.isSimilar(item)) {
                    itemAmount += inventoryItem.getAmount();
                    hasItem = true;
                }
            }

            if (hasItem) {
                if (itemCount <= itemAmount) {
                    for (int i = 0; i < itemCount; i++) {
                        player.getInventory().removeItem(item);
                    }

                    // 등록한 아이템을 가져옵니다
                    try {
                        ItemStack items = plugin.getConfig().getItemStack("name");
                        player.getInventory().addItem(items);
                    } catch (Exception e) {
                        player.sendMessage("가챠를 등록하셔야 합니다.");
                        e.printStackTrace();
                    }
                } else {
                    player.sendMessage("수량이 부족합니다.");
                }
            } else {
                player.sendMessage("아이템이 없습니다.");
            }
        }
    }
}