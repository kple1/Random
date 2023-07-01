package io.random.Listener;

import io.random.Utils.Color;
import io.random.Utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.random.Main.plugin;

public class RightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        // 우클릭한 플레이어
        Player player = event.getPlayer();
        // 우클릭한 아이템
        ItemStack item = event.getItem();

        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (item == null || item.getType() == Material.AIR) {
                return;
            }

            boolean found = false;
            for (int j = 0; j < 54; j++) {
                ItemStack name = plugin.getConfig().getItemStack("RightClick." + j + ".item");
                if (item != null && name != null && item.isSimilar(name)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return;
            }

            Map<ItemStack, Double> itemProbabilities = new HashMap<>();

            for (int i = 0; i < 54; i++) {
                ItemMeta itemMeta = item.getItemMeta();
                String lore = itemMeta.getLore().toString().replace("[", "").replace("]", "");
                ItemStack items = plugin.getConfig().getItemStack(lore + "." + i + ".item");
                double probability = plugin.getConfig().getDouble(lore + "." + i + ".ran");

                // items와 probability 값에 대한 처리
                itemProbabilities.put(items, probability);
            }

            // 여러 아이템 중 정해진 확률로 한 개의 아이템을 획득
            String obtainedItem = ItemUtil.addItemWithProbability(player, itemProbabilities);

            ItemStack items = player.getInventory().getItemInMainHand();
            if (items.getAmount() > 1) {
                items.setAmount(item.getAmount() - 1);
            } else {
                player.getInventory().removeItem(items);
            }

            if (obtainedItem != null) {
                for (int i = 0; i <= 100; i++) {
                    ItemMeta itemMeta = item.getItemMeta();
                    String lore = itemMeta.getLore().toString().replace("[", "").replace("]", "");

                    // obtainedItem의 이름과 일치하는 아이템을 가져옵니다.
                    String var0 = plugin.getConfig().getString(lore + " 공지설정." + i + ".itemName");
                    if (var0 == null) {
                        continue;
                    }

                    // obtainedItem의 이름과 일치하는 아이템이 var0와 같은 경우 브로드캐스트를 출력하고 루프를 종료
                    if (Objects.equals(obtainedItem, var0)) {
                        Bukkit.broadcastMessage(Color.chat("&5&l&o[치장뽑기] &a&l" + player.getName() + "&f님이&a&l " + obtainedItem + "&f을 뽑았습니다."));
                        break;
                    }
                }
            }
        }
    }
}
