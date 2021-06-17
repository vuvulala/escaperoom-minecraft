package escapecraft.escapecraft;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
    private Player gamerboi;
    private List<QuestionObject> questionObjects = new ArrayList();
    private int questionIndex = 0;
    private BossBar bossbar;

    public Gamer(Player player, List<QuestionObject> newQuestionObject) {
        this.gamerboi = player;
        this.questionObjects.addAll(newQuestionObject);
        this.createBossbar();
    }

    public Gamer(Player player) {
        this.gamerboi = player;
        this.createBossbar();
    }

    public boolean startQuiz() {
        this.reset();
        this.bossbar.setTitle(this.getQuestion().getTitle());
        this.bossbar.addPlayer(this.gamerboi);
        this.teleport();
        return true;
    }

    public int increase() {return this.questionIndex++;}
    public void reset() {this.questionIndex = 0;}
    public Boolean addQuestionObject(QuestionObject answerObject) { return this.questionObjects.add(answerObject); }
    public Boolean addAllQuestionObjects(List<QuestionObject> answerObjects) { return this.questionObjects.addAll(answerObjects); }
    public Boolean setAllQuestions(ArrayList<QuestionObject> questions) {
        this.questionIndex = 0;
        this.questionObjects.clear();
        this.questionObjects.addAll(questions);
        return true;
    }

    public QuestionObject getQuestion() { return this.questionObjects.get(this.questionIndex); }
    public QuestionObject getQuestion(Integer questionIndex) {
        if(questionIndex >= this.questionObjects.size()) return new QuestionObject();
        return this.questionObjects.get(questionIndex);
    };

    public Player getPlayer() {
        return this.gamerboi;
    }

    public AnswerObject getAnswer(Integer answerIndex) {
        try {
            if (answerIndex >= this.questionObjects.size()) return new AnswerObject("", false);
            return this.questionObjects.get(this.questionIndex).getAnswer(answerIndex);
        } catch (Exception e) {return new AnswerObject("", false);}
    }
    public AnswerObject signTextToAnswer (String answerString) {
        int answerIndex = 0;
        for (int i=0; i<4; i++) {
            if (answerString.compareToIgnoreCase("{answer_" + (i+1) + "}") == 0) {
                answerIndex = i;
            }
        }
        return getAnswer(answerIndex);
    }

    public boolean nextQuestion() {
        this.increase();
        if (this.questionIndex >= this.questionObjects.size()) {
            this.bossbar.removeAll();
            this.gamerboi.performCommand("/spawn");
            return false;
        }
        this.bossbar.setTitle(this.getQuestion().getTitle());
        this.teleport();
        return true;
    }

    public void teleport() {
        JsonElement jsonElement = JsonLoader.getElement("./plugins/escapecraft/escaperoom/room1.json");
        System.out.println(jsonElement);
        jsonElement = jsonElement.getAsJsonArray().get(questionIndex).getAsJsonObject();
        int posX = jsonElement.getAsJsonObject().get("x").getAsInt();
        int posY = jsonElement.getAsJsonObject().get("y").getAsInt();
        int posZ = jsonElement.getAsJsonObject().get("z").getAsInt();

        this.gamerboi.teleport(new Location(this.gamerboi.getWorld(), posX, posY, posZ));
    }

    private void createBossbar() {
        String title = "";
        BarColor color = BarColor.RED;
        BarStyle style = BarStyle.SOLID;

        this.bossbar = Bukkit.createBossBar(title, color, style);
    }
}
