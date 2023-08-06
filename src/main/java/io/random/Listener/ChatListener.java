package io.random.Listener;

import io.random.Main;
import io.random.Utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {

    private final Main plugin;
    private final Player player;
    private final String title;
    private final int i;
    String titles = Color.chat("&f[ &b가챠 &f] ");

    public ChatListener(Player player, String title, int i) {
        this.plugin = Main.getPlugin();
        this.player = player;
        this.title = title;
        this.i = i;
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();

        if (!Pattern.matches("-?\\d+(\\.\\d+)?", message)) {
            player.sendMessage(titles + "입력한 값이 숫자가 아닙니다.");
            event.setCancelled(true);
            return;
        }

        double number = Double.parseDouble(message);
        if (!message.isEmpty()) {
            plugin.getConfig().set(title + "." + i + ".ran", number);
            plugin.saveConfig();
            player.sendMessage(titles + "확률 설정이 완료되었습니다.");
        }

        HandlerList.unregisterAll(this);
        event.setCancelled(true);
    }
}
