package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.utils.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class WhiteFillerComponent extends ItemComponent {
    public WhiteFillerComponent(String name) {
        super(name);
    }

    @Override
    public void handleInventoryClick(InventoryClickEvent clickEvent) {
        clickEvent.setCancelled(true);
    }
}
