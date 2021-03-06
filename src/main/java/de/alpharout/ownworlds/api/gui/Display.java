package de.alpharout.ownworlds.api.gui;

import de.alpharout.ownworlds.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Display {
    private int size;
    private Inventory inventory;
    private View currentView;
    private Player player;
    private DisplayExtension displayExtension;

    public Display(Player player, String name, int size) {
        this.player = player;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public void setDisplayExtension(DisplayExtension displayExtension) {
        this.displayExtension = displayExtension;
    }

    public void setView(View view) {
        this.currentView = view;

        if (view.getComponentMap(player).size() > inventory.getSize()) {
            Log.error("Tried to set a too big view to a display.");
            return;
        }

        for (int position : view.getComponentMap(player).keySet()) {
            inventory.setItem(position, view.getComponentMap(player).get(position).getItemStack(player));
        }
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        if (displayExtension != null) return displayExtension.processInventory(inventory);
        else return inventory;
    }
}
