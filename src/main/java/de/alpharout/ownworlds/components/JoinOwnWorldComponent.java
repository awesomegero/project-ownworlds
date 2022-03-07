package de.alpharout.ownworlds.components;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.Level;
import de.alpharout.ownworlds.api.User;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class JoinOwnWorldComponent extends ItemComponent {

    public JoinOwnWorldComponent(String name) {
        super(name);
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return super.getItemStack(player);
    }

    @Override
    public void handleInventoryClick(InventoryClickEvent clickEvent) {
        clickEvent.setCancelled(true);

        Player player = (Player) clickEvent.getWhoClicked();
        Level level = new Level(UUID.randomUUID(), new User(player));
        level.create(World.Environment.NORMAL);
    }
}
