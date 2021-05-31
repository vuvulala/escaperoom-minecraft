package escapecraft.escapecraft.managers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {
    public static boolean chatEnabled = true;

    @EventHandler
    public void AsyncChatEvent(AsyncPlayerChatEvent event) {
        event.setCancelled(!chatEnabled);
    }
}
