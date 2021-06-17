package escapecraft.escapecraft.events;

import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CheckAnswerEvent implements Listener {

    @EventHandler
    public void signInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!Tag.SIGNS.isTagged(event.getClickedBlock().getType())) return;


        Sign sign = (Sign) event.getClickedBlock().getState();
        event.getPlayer().sendMessage(sign.line(0));
    }

}
