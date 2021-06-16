package escapecraft.escapecraft;

import com.empcraft.InSignsPlus;
import com.empcraft.Placeholder;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class SignPlaceholders {

    public void SignPlaceholders() {
        Plugin inSignsPlus = getServer().getPluginManager().getPlugin("InSignsPlus");
        if ((inSignsPlus != null) && inSignsPlus.isEnabled()) {
            InSignsPlus ISP = (InSignsPlus) inSignsPlus;
            ISP.addPlaceholder(new Placeholder("answer_1") {
                @Override
                public String getValue(Player player, Location location, String[] modifiers, Boolean elevation) {
                    return "" + ChatManager.chatEnabled;
                }
            });
        }
    }

}
