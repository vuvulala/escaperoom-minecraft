package escapecraft.escapecraft;

import escapecraft.escapecraft.commands.*;
import escapecraft.escapecraft.events.CheckAnswerEvent;
import escapecraft.escapecraft.events.EscaperoomEvents;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Escapecraft extends JavaPlugin implements Listener {

    private final String testString = "Dette er en test!";

    public void onEnable() {
        // Plugin startup logic
        new ChatCommand();

        Gamer gamer;
        /*ArrayList<AnswerObject> answerObjects = new ArrayList();
        System.out.println("Assembling gamer..");
        answerObjects.add(new AnswerObject("svar 1", false));
        answerObjects.add(new AnswerObject("svar 2", false));
        answerObjects.add(new AnswerObject("svar 3", false));
        answerObjects.add(new AnswerObject("svar 4", true ));
        ArrayList<QuestionObject> qu = new ArrayList();
        qu.add(new QuestionObject(answerObjects));
        System.out.println(qu.size());*/

        ArrayList<QuestionObject> questions = QuizLoader.getQuiz("idk");

        gamer = new Gamer(questions);
        System.out.println("Assembly complete!");

        System.out.println(gamer.getQuestion(1));

        for (Player player : Bukkit.getOnlinePlayers()) {
            Escaperoom.addGamer(player, gamer);
        }

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
        Bukkit.getPluginManager().registerEvents(new CheckAnswerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EscaperoomEvents(), this);
        new SignPlaceholders();

    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}