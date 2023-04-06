package seedu.address.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents an inventory, belonging to an Entity.
 */
public class Inventory {
    private final List<Item> items = new ArrayList<>();
    private int totalValue = 0;

    // Default constructor
    public Inventory() {
    }

    /**
     * Given a list of items, initialize inventory.
     */
    public Inventory(List<Item> items) {
        this.items.addAll(items);
        updateValue();
    }

    /**
     * Returns an empty inventory.
     */
    public static Inventory emptyInventory() {
        return new Inventory();
    }

    /**
     * Add item to the inventory
     */
    public void addItem(Item item) {
        items.add(item);
        updateValue();
    }

    /**
     * Delete item from the inventory
     */
    public void deleteItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            throw new PersonNotFoundException();
        }
        updateValue();
    }

    /**
     * Given the name of the item, delete the item from the inventory.
     */
    public void deleteItem(String itemName) {
        if (hasItem(itemName)) {
            Item item = items.stream().filter(entity -> entity.getName().fullName.equals(itemName)).findFirst().get();
            deleteItem(item);
        } else {
            throw new PersonNotFoundException();
        }
        updateValue();
    }

    /**
     * Given the name of the item, return true if item exist inside inventory.
     */
    public boolean hasItem(String itemName) {
        return items.stream().filter(entity -> entity.getName().fullName.equals(itemName)).findAny().isPresent();
    }


    /**
     * Returns unmodifiable list of items contained by the inventory.
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns total value of the inventory
     */
    public int getTotalValue() {
        return totalValue;
    }

    private void updateValue() {
        int cost = 0;
        for (Item item : items) {
            cost += item.getCost();
        }
        totalValue = cost;
    }


}
