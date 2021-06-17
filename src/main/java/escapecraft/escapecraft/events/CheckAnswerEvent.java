package escapecraft.escapecraft.events;

import escapecraft.escapecraft.Escaperoom;
import org.bukkit.Tag;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CheckAnswerEvent implements Listener {

    @EventHandler
    public void signInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!Tag.SIGNS.isTagged(event.getClickedBlock().getType())) return;


        Player player = event.getPlayer();
        Sign sign = (Sign) event.getClickedBlock().getState();
        String signText = sign.getLine(0);

        boolean temp = Escaperoom.getGamer(player).signTextToAnswer(signText).isCorrect();
        if (temp) Escaperoom.getGamer(player).nextQuestion();
    }

}
