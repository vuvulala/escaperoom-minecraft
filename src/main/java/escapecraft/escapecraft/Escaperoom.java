package escapecraft.escapecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Escaperoom {
    private final List<Player> players = new ArrayList();
    private final Map<Player, Gamer> gamers = new HashMap();

    Escaperoom() {

    }

    public Boolean addPlayer(Player player) { return this.players.add(player); }
    public Boolean removePlayer(Player player) { return this.players.remove(player); }
    public List<Player> GetPlayers() { return this.players; }

    public Gamer addGamer(Player player, Gamer gamer) { return this.gamers.put(player, gamer); }
    public Gamer removeGamer(Player player) { return this.gamers.remove(player); }
    public Gamer getGamer(Player player) { return this.gamers.get(player); }


}
