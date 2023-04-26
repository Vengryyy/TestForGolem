package la.vengryyy.testforgolem.events;

import la.vengryyy.testforgolem.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {
    private final Main plugin;

    public onPlayerJoin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Event(PlayerJoinEvent event) {
        if(!this.plugin.db.playerExists(event.getPlayer().getName())) {
            this.plugin.db.addPlayer(event.getPlayer().getName());
        }
    }
}
