package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinPublicWorldComponent extends ItemComponent {
    public JoinPublicWorldComponent(String name) {
        super(name);
    }

    @Override
    public void handleInventoryClick(InventoryClickEvent clickEvent) {
        clickEvent.setCancelled(true);
    }
}
