package de.alpharout.ownworlds.view;

import de.alpharout.ownworlds.api.ItemComponent;
import de.alpharout.ownworlds.api.gui.View;

import java.util.HashMap;

public class MainView extends View {
    @Override
    public HashMap<Integer, ItemComponent> getComponentMap() {
        addComponent(11, ItemComponent.getItemComponent("join-public-world"));
        addComponent(15, ItemComponent.getItemComponent("join-private-world"));
        addComponent(13, ItemComponent.getItemComponent("join-own-world"));

        return super.getComponentMap();
    }
}
