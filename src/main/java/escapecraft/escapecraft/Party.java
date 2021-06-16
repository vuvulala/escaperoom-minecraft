package escapecraft.escapecraft;

import com.google.gson.JsonObject;
import escapecraft.escapecraft.managers.QuestionManager;
import escapecraft.escapecraft.managers.AnswerManager;
import escapecraft.escapecraft.managers.ChatManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import escapecraft.escapecraft.Answer;
import org.bukkit.scoreboard.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

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

    public void sendMessage(TextComponent message) { //stiffy
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
            currentQuestion.put(currentPlayer, 0);
            currentScore.put(currentPlayer, 0);
        }

        sendMessage(StringUtils.repeat(" \n", 100));
        sendMessage(AnswerManager.getAnswers(_quiz, 0, _name));
        updateScoreboard();
        return true;
    }

    public void answer(Player player, Integer questionNumber, Integer answerNumber, Boolean correct) {
        player.sendMessage(StringUtils.repeat(" \n", 100));

        ArrayList<Answer> current = result.get(player);
        Integer oldScore = currentScore.get(player);
        Integer newScore = correct ? 1 : 0;
        current.add(new Answer(questionNumber, answerNumber, correct));

        result.replace(player, current);
        currentQuestion.replace(player, questionNumber+1);
        currentScore.replace(player, oldScore + newScore);


        updateScoreboard();
        if (questionNumber + 1 == _quizLength) { printScore(player); return; }
        player.sendMessage(AnswerManager.getAnswers(_quiz, questionNumber + 1, _name));
    }

    public void printScore(Player player) {
            Integer maxPoints = _quizLength;
            Integer points = 0;
            for (Answer answer : result.get(player)) { points += (answer.correct) ? 1 : 0; }
            player.sendMessage("You got " + points + " out of " + maxPoints + " points!");
    }

    public void updateScoreboard() {
        // Sort the scores in descending order
        List<Player> keys = currentScore.entrySet()
                .stream()
                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed())
                .limit(3).map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Create the scoreboard
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective sObj = scoreboard.registerNewObjective("Leaderboard", "dummy");
        sObj.setDisplaySlot(DisplaySlot.SIDEBAR);
        try {
            Score firstPlace = sObj.getScore(GenerateScoreboardMessage(keys.get(0)));
            firstPlace.setScore(2);
            Score secondPlace = sObj.getScore(GenerateScoreboardMessage(keys.get(1)));
            secondPlace.setScore(1);
            Score thirdPlace = sObj.getScore(GenerateScoreboardMessage(keys.get(2)));
            thirdPlace.setScore(0);
        } catch(Exception e) {};


        for (Player player : _members) player.setScoreboard(scoreboard);
    }

    private String GenerateScoreboardMessage(Player player) {
        String suffix = "";

        if (currentQuestion.get(player) >= _quizLength) {
            suffix = "Completed!";
        }

        return currentScore.get(player) + " " + ChatColor.YELLOW + player.getName() + " " + ChatColor.BOLD + suffix;
    }

    private Map<Player, ArrayList<Answer>> result = new HashMap<>() {};
    private Map<Player, Integer> currentQuestion = new HashMap<>() {};
    private Map<Player, Integer> currentScore = new HashMap<>() {};

    public String _name;
    public CommandSender _admin;
    private ArrayList<Player> _members = new ArrayList<Player>();
    private JsonObject _quiz;
    private Integer _quizLength;
}
