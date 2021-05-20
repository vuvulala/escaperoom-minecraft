package escapecraft.escapecraft;

import escapecraft.escapecraft.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;



public final class Escapecraft extends JavaPlugin implements Listener {


    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
        this.getCommand("disable").setExecutor(new DisablePlayerSendChat());
        this.getCommand("enable").setExecutor(new EnablePlayerSendChat());
        this.getCommand("quest").setExecutor(new SendQuestionCommand());
    }

    public void onDisable() {
        // Plugin shutdown logic
    }



}