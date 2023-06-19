package io.random.Listener;

import io.random.Main;
import io.random.Utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

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
        if (message != null && !message.isEmpty()) {
            plugin.getConfig().set(title + "." + i + ".ran", Double.parseDouble(message));
            plugin.saveConfig();
            player.sendMessage(titles + "확률 설정이 완료되었습니다.");
        }

        // 이벤트 핸들러를 제거합니다.
        HandlerList.unregisterAll(this);
        event.setCancelled(true);
    }
}
