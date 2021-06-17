package escapecraft.escapecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Escaperoom {
    private static final List<Player> players = new ArrayList();
    private static final Map<Player, Gamer> gamers = new HashMap();

    Escaperoom() {

    }

    public static Boolean addPlayer(Player player) { return players.add(player); }
    public static Boolean removePlayer(Player player) { return players.remove(player); }
    public static List<Player> GetPlayers() { return players; }

    public static Gamer addGamer(Player player, Gamer gamer) { return gamers.put(player, gamer); }
    public static Gamer removeGamer(Player player) { return gamers.remove(player); }
    public static Gamer getGamer(Player player) { return gamers.get(player); }


}
