package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent) {
        if (interactEvent.getItem() == null) return;
        if (interactEvent.getItem().getItemMeta() == null) return;
        if (interactEvent.getAction() == Action.RIGHT_CLICK_AIR ||
                interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemComponent itemComponent = ItemComponent.getItemComponentByDisplayName(interactEvent.getPlayer(), interactEvent.getItem().getItemMeta().getDisplayName());
            if (itemComponent != null) {
                itemComponent.handleClick(interactEvent);
            }
        }
    }
}
