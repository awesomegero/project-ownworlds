package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class JoinPrivateWorldComponent extends ItemComponent {
    public JoinPrivateWorldComponent(String name) {
        super(name);
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return super.getItemStack(player);
    }

    @Override
    public void handleInventoryClick(InventoryClickEvent clickEvent) {
        clickEvent.setCancelled(true);
    }
}
