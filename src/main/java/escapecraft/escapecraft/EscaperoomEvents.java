package escapecraft.escapecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class EscaperoomEvents implements Listener {
    Escaperoom escaperoom;

    EscaperoomEvents(Escaperoom escaperoom) {
        this.escaperoom = escaperoom;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        List<QuestionObject> questionObjects = new ArrayList();
        Player player = event.getPlayer();
        Gamer gamer;

        try {
            System.out.println("player joined. Trying to assemble gamer..");
            questionObjects.add(new QuestionObject("svar 1", false));
            questionObjects.add(new QuestionObject("svar 2", false));
            questionObjects.add(new QuestionObject("svar 3", false));
            questionObjects.add(new QuestionObject("svar 4", true));
            gamer = new Gamer(questionObjects);
            System.out.println("gaming!");
            System.out.println(gamer.getQuestion(1));

            escaperoom.addPlayer(player);
            escaperoom.addGamer(player, gamer);
//            System.out.println(escaperoom.GetGamer(player).getQuestion(1));

            } catch(Exception e) {System.out.println(e);}
        }
    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent event) {
        try {
        System.out.println("Player left the gaem.,.");
        Player player = event.getPlayer();
        escaperoom.removePlayer(player);
        escaperoom.removeGamer(player);
        } catch(Exception e) {System.out.println(e);}
    }
}
