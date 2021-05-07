package escapecraft.escapecraft;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Escapecraft extends JavaPlugin implements Listener {

    public void onEnable() {
        // Plugin startup logic
        this.getCommand("test").setExecutor(new CommandTest());
    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
