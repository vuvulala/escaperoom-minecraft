package escapecraft.escapecraft.managers;

import escapecraft.escapecraft.classes.Gamer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Escaperoom {
    private static final Map<Player, Gamer> gamers = new HashMap();

    Escaperoom() {

    }

    public static Gamer addGamer(Player player, Gamer gamer) { return gamers.put(player, gamer); }
    public static Gamer removeGamer(Player player) { return gamers.remove(player); }
    public static Gamer getGamer(Player player) { return gamers.get(player); }
    public static Map<Player, Gamer> getGamers() { return gamers; }


}
