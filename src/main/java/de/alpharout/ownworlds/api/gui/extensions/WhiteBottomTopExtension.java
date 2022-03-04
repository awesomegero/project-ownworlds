package de.alpharout.ownworlds.api.gui.extensions;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.gui.DisplayExtension;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WhiteBottomTopExtension implements DisplayExtension {

    @Override
    public Inventory processInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (
                    (i >= 0 && i < 9)
                    || (i >= (inventory.getSize() - 9) && i < inventory.getSize())
            ) {
                inventory.setItem(i, ItemComponent.getItemComponent("white-filler").getItemStack(null));
            }
        }
        return inventory;
    }
}
