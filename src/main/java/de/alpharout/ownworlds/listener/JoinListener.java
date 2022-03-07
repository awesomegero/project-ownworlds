package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        if (User.getUserMap().get(joinEvent.getPlayer().getUniqueId()) == null) User.getUserMap().put(joinEvent.getPlayer().getUniqueId(), new User(joinEvent.getPlayer()));
        joinEvent.getPlayer().getInventory().setItem(8, ItemComponent.getItemComponent("switcher").getItemStack(joinEvent.getPlayer()));
    }
}
