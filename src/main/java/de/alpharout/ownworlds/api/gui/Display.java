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

    public Display(Player player, String name, int size) {
        this.player = player;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public void setView(View view) {
        this.currentView = view;

        if (view.getComponentMap().size() > inventory.getSize()) {
            Log.error("Tried to set a too big view to a display.");
            return;
        }

        for (int position : view.getComponentMap().keySet()) {
            inventory.setItem(position, view.getComponentMap().get(position).getItemStack(player));
        }
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }
}