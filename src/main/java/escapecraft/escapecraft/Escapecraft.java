package escapecraft.escapecraft;

import escapecraft.escapecraft.commands.*;
import escapecraft.escapecraft.events.CheckAnswerEvent;
import escapecraft.escapecraft.events.EscaperoomEvents;
import escapecraft.escapecraft.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Escapecraft extends JavaPlugin implements Listener {
    public void onEnable() {
        // Plugin startup logic
        new ChatCommand();
        new StartCommand();

        Gamer gamer = new Gamer();

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
        System.out.println("You dare disable me? FOOL! I will never die!!");
    }
}