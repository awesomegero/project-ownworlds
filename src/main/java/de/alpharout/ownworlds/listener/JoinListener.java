package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        joinEvent.getPlayer().getInventory().setItem(8, ItemComponent.getItemComponent("switcher").getItemStack(joinEvent.getPlayer()));
    }
}
