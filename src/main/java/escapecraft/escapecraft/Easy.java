package escapecraft.escapecraft;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

public class Easy {

    static public int broadcast(String string) {
        return Bukkit.getServer().broadcastMessage(string);
    }
}
