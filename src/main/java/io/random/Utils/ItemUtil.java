package io.random.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ItemUtil {

    static String title = Color.chat("&f[ &b가챠 &f] ");

    // 여러 아이템 중 정해진 확률로 한 개의 아이템을 획득하는 메서드
    public static String addItemWithProbability(Player player, Map<ItemStack, Double> itemProbabilities) {
        double totalProbability = 0.0;
        for (double probability : itemProbabilities.values()) {
            totalProbability += probability;
        }

        double randomValue = Math.random() * totalProbability;

        double cumulativeProbability = 0.0;
        for (Map.Entry<ItemStack, Double> entry : itemProbabilities.entrySet()) {
            ItemStack item = entry.getKey();
            double probability = entry.getValue();

            cumulativeProbability += probability;
            if (randomValue < cumulativeProbability) {
                player.getInventory().addItem(item);
                player.sendMessage(title + "아이템을 획득했습니다!");

                // 획득한 아이템의 이름을 리턴합니다.
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta.hasDisplayName()) {
                    return itemMeta.getDisplayName();
                } else {
                    return item.getType().toString(); // 아이템의 이름이 없는 경우 아이템의 타입을 문자열로 리턴합니다.
                }
            }
        }
        return null;
    }
}


