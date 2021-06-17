package escapecraft.escapecraft.events;

import escapecraft.escapecraft.classes.AnswerObject;
import escapecraft.escapecraft.classes.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class EscaperoomEvents implements Listener {
    escapecraft.escapecraft.managers.Escaperoom Escaperoom;

    public EscaperoomEvents() {
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        List<AnswerObject> answerObjects = new ArrayList();
        Player player = event.getPlayer();
        Gamer gamer = new Gamer(player);
        Escaperoom.addGamer(player, gamer);
        }
    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent event) {
        try {
        Player player = event.getPlayer();
        Escaperoom.removeGamer(player);
        } catch(Exception e) {System.out.println(e);}
    }
}
