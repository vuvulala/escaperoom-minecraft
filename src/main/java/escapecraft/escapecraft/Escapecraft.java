package escapecraft.escapecraft;

import com.empcraft.InSignsPlus;
import com.empcraft.Placeholder;
import escapecraft.escapecraft.commands.*;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Escapecraft extends JavaPlugin implements Listener {

    private final String testString = "Dette er en test!";

    public void onEnable() {
        // Plugin startup logic
        new ChatCommand();

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
        new SignPlaceholders();

    }

    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void signInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();

        if (!Tag.SIGNS.isTagged(block.getType())) return;

        Sign sign = (Sign) block.getState();
        event.getPlayer().sendMessage(sign.line(0));
    }
}