package de.alpharout.ownworlds.listener;

import de.alpharout.ownworlds.OwnWorlds;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent dropItemEvent) {
        // Check if item is switcher
        {
            String rawItemName = OwnWorlds.getConfigManager().getMessageConf().getString("switcher.name");
            if (rawItemName == null) return;
            String itemName = ChatColor.translateAlternateColorCodes('&', rawItemName);
            if (dropItemEvent.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(itemName)) dropItemEvent.setCancelled(true);
        }
    }
}
