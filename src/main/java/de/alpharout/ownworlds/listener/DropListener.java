package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent dropItemEvent) {
        ItemComponent itemComponent = ItemComponent.getItemComponentByDisplayName(dropItemEvent.getPlayer(), dropItemEvent.getItemDrop().getItemStack().getItemMeta().getDisplayName());
        if (itemComponent != null) itemComponent.handleDrop(dropItemEvent);
    }
}
