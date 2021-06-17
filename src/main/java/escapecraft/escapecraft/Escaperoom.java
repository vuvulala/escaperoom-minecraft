package escapecraft.escapecraft;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Escaperoom {
    private static final Map<Player, Gamer> gamers = new HashMap();

    Escaperoom() {

    }

    public static Gamer addGamer(Player player, Gamer gamer) { return gamers.put(player, gamer); }
    public static Gamer removeGamer(Player player) { return gamers.remove(player); }
    public static Gamer getGamer(Player player) { return gamers.get(player); }

    public static boolean signAnswer(int answerIndex, Player player) {
        return getGamer(player).getAnswer(answerIndex).isCorrect();
    }
}
