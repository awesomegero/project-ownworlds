package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreateWorldComponent extends ItemComponent {
    public CreateWorldComponent(String name) {
        super(name);
    }

    @Override
    public ItemStack getItemStack(Player player) {
        // Item when player has no world
        if (true) {
            return super.getItemStack(player);
        }

        // Item when player has an existing world
        return null;
    }
}
