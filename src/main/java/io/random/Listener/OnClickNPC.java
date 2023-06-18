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
            if (itemCount > 0) {
                int amountInInventory = countItemInInventory(player, Material.BOOK);
                if (amountInInventory >= itemCount) {
                    removeItemFromInventory(player, Material.BOOK, itemCount);
                    player.getInventory().addItem(item);
                } else {
                    player.sendMessage("수량이 부족합니다.");
                }
            } else {
                player.sendMessage("수량을 설정해야 합니다.");
            }
        }
    }

    private int countItemInInventory(Player player, Material material) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                count += item.getAmount();
            }
        }
        return count;
    }

    private void removeItemFromInventory(Player player, Material material, int amount) {
        int remaining = amount;
        ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getType() == material) {
                int itemAmount = item.getAmount();
                if (remaining >= itemAmount) {
                    remaining -= itemAmount;
                    contents[i] = null;
                } else {
                    item.setAmount(itemAmount - remaining);
                    break;
                }
            }
        }
        player.getInventory().setContents(contents);
    }
}
