package escapecraft.escapecraft.events;

import escapecraft.escapecraft.AnswerObject;
import escapecraft.escapecraft.Escaperoom;
import escapecraft.escapecraft.Gamer;
import escapecraft.escapecraft.QuestionObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class EscaperoomEvents implements Listener {
    escapecraft.escapecraft.Escaperoom Escaperoom;

    public EscaperoomEvents() {
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        List<AnswerObject> answerObjects = new ArrayList();
        Player player = event.getPlayer();
        Gamer gamer;

        try {
            System.out.println("player joined. Trying to assemble gamer..");

            ArrayList<AnswerObject> tmpAnswers = new ArrayList();
            System.out.println("Assembling gamer..");
            answerObjects.add(new AnswerObject("svar 1", false));
            answerObjects.add(new AnswerObject("svar 2", false));
            answerObjects.add(new AnswerObject("svar 3", false));
            answerObjects.add(new AnswerObject("svar 4", true ));
            ArrayList<QuestionObject> qu = new ArrayList<QuestionObject>();
            qu.add(new QuestionObject(tmpAnswers));

            gamer = new Gamer(qu);
            System.out.println("gaming!");
            System.out.println(gamer.getQuestion(1));

            Escaperoom.addPlayer(player);
            Escaperoom.addGamer(player, gamer);
//            System.out.println(escaperoom.GetGamer(player).getQuestion(1));

            } catch(Exception e) {System.out.println(e);}
        }
    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent event) {
        try {
        System.out.println("Player left the gaem.,.");
        Player player = event.getPlayer();
        Escaperoom.removePlayer(player);
        Escaperoom.removeGamer(player);
        } catch(Exception e) {System.out.println(e);}
    }
}
