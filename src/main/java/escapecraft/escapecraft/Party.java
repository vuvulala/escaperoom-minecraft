package escapecraft.escapecraft;

import com.google.gson.JsonObject;
import escapecraft.escapecraft.managers.AnswerManager;
import escapecraft.escapecraft.managers.QuestionManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import escapecraft.escapecraft.Answer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Party {
    public Party(String name, CommandSender admin) {
        _name = name;
        _admin = admin;
    }

    public void addPlayer(Player player)  {
        _members.add(player);
    }

    public void addPlayers(Collection<Player> players) {
        for(Player currentPlayer : players) {
            if(_members.contains(currentPlayer)) continue;
            _members.add(currentPlayer);
        }
    }

    public void removePlayers(Collection<Player> players) {
        for(Player currentPlayer : players) {
            if(! _members.contains(currentPlayer)) continue;
            _members.remove(currentPlayer);
        }
    }

    public void sendMessage(String message) {
        for(Player current : _members) {
            current.sendMessage(message);
        }
    }

    public void sendMessage(TextComponent message) {
        for(Player current : _members) {
            current.sendMessage(message);
        }
    }

    public ArrayList<String> getMembersAsStringArray() {
        ArrayList<String> names = new ArrayList<String>();
        for(Player currentPlayer : _members) {
            names.add(currentPlayer.getName());
        }
        return names;
    }

    public void assignQuiz(String quizName) {
        System.out.println("assigning quiz party");
        if(! QuestionManager.questionExists(quizName)) return;
        System.out.println("really doin it now!");
        _quiz = QuestionManager.getQuestion(quizName);
        _quizLength = _quiz.getAsJsonArray("questions").size();
    }

    public boolean start() {
        if(_quiz == null) return false;

        for(Player currentPlayer : _members) {
            result.put(currentPlayer, new ArrayList<Answer>());
        }

        sendMessage(AnswerManager.getAnswers(_quiz, 0, _name));
        return true;
    }

    public void answer(Player player, Integer questionNumber, Integer answerNumber, Boolean correct) {
        ArrayList<Answer> current = result.get(player);
        current.add(new Answer(questionNumber, answerNumber, correct));
        result.replace(player, current);
        if(questionNumber + 1 == _quizLength) return;
        player.sendMessage(AnswerManager.getAnswers(_quiz, questionNumber + 1, _name));
    }

    private Map<Player, ArrayList<Answer>> result = new HashMap<>() {
    };

    public String _name;
    public CommandSender _admin;
    private ArrayList<Player> _members = new ArrayList<Player>();
    private JsonObject _quiz;
    private Integer _quizLength;
}
