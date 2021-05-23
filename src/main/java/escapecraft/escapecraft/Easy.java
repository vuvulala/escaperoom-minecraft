package escapecraft.escapecraft;

import org.bukkit.Bukkit;

public class Easy {

    static public int broadcast(String string) {
        return Bukkit.getServer().broadcastMessage(string);
    }
}
