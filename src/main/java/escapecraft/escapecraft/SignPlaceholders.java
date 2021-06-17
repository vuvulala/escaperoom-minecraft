package escapecraft.escapecraft;

import com.empcraft.InSignsPlus;
import com.empcraft.Placeholder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class SignPlaceholders {
    public final Integer maxAnswers = 4;

    SignPlaceholders() {
        Plugin inSignsPlus = getServer().getPluginManager().getPlugin("InSignsPlus");
        if ((inSignsPlus != null) && inSignsPlus.isEnabled()) {
            InSignsPlus ISP = (InSignsPlus) inSignsPlus;
            for (int index = 0; index < maxAnswers; index++) {
                int answerNumber = index + 1;
                int finalIndex = index;
                ISP.addPlaceholder(new Placeholder("answer_" + answerNumber) {
                    @Override public String getValue(Player player, Location location, String[] modifiers, Boolean elevation) {
                        return "" + Escaperoom.getGamer(player).getAnswer(finalIndex);
                    }
                });
            }
        }
    }

}
