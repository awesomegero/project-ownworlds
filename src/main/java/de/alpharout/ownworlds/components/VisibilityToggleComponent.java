package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.api.ItemComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VisibilityToggleComponent extends ItemComponent {
    public VisibilityToggleComponent(String name) {
        super(name);
    }

    @Override
    public ItemStack getItemStack(Player player) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
