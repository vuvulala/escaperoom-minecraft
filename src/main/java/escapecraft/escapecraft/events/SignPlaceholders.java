package escapecraft.escapecraft.events;

import com.empcraft.InSignsPlus;
import com.empcraft.Placeholder;
import escapecraft.escapecraft.managers.Escaperoom;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class SignPlaceholders {
    public final Integer maxAnswers = 4;

    public SignPlaceholders() {
        Plugin inSignsPlus = getServer().getPluginManager().getPlugin("InSignsPlus");
        if ((inSignsPlus != null) && inSignsPlus.isEnabled()) {
            InSignsPlus ISP = (InSignsPlus) inSignsPlus;
            for (int index = 0; index < maxAnswers; index++) {
                int answerNumber = index + 1;
                int finalIndex = index;
                System.out.println("adding placeholder called " + answerNumber);
                ISP.addPlaceholder(new Placeholder("answer_" + answerNumber) {
                    @Override public String getValue(Player player, Location location, String[] modifiers, Boolean elevation) {
                        return "" + Escaperoom.getGamer(player).getAnswer(finalIndex).getText();
                    }
                });
            }
        }
    }

}
