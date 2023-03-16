package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

// Driver function.

/**
 * Reroll...
 */
public class Reroll implements ReadOnlyReroll {

    private final RerollCharacters characters;
    private final RerollItems items;
    private final RerollMobs mobs;

    {
        characters = new RerollCharacters();
        items = new RerollItems();
        mobs = new RerollMobs();
    }

    public Reroll() {
    }

    /**
     * Create Reroll from existing copy
     *
     * @param toBeCopied
     */
    public Reroll(ReadOnlyReroll toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Reset data to newData
     *
     * @param newData
     */
    public void resetData(ReadOnlyReroll newData) {
        requireNonNull(newData);

        characters.resetData(newData.getCharacters());
        items.resetData(newData.getItems());
        mobs.resetData(newData.getMobs());
    }

    @Override
    public ReadOnlyEntities<Item> getItems() {
        return items;
    }

    @Override
    public ReadOnlyEntities<Character> getCharacters() {
        return characters;
    }

    @Override
    public ReadOnlyEntities<Mob> getMobs() {
        return mobs;
    }

    // Entity level operations ===============

    /**
     * Has entity
     *
     * @param e
     * @return
     */
    public boolean hasEntity(Entity e) {
        // Switch till it works
        if (e instanceof Item) {
            return items.hasEntity((Item) e);
        } else if (e instanceof Character) {
            return characters.hasEntity((Character) e);
        } else if (e instanceof Mob) {
            return mobs.hasEntity((Mob) e);
        } else {
            // Should throw error
            return false;
        }
    }

    /**
     * Add Entity
     *
     * @param e
     */
    public void addEntity(Entity e) {
        System.out.println("adding " + e.getName());
        if (e instanceof Item) {
            System.out.println("adding item" + e.getName());
            items.addEntity((Item) e);
        } else if (e instanceof Character) {
            System.out.println("adding character" + e.getName());
            characters.addEntity((Character) e);
        } else if (e instanceof Mob) {
            System.out.println("adding mob" + e.getName());
            mobs.addEntity((Mob) e);
        } else {

        }
    }

    /**
     * Set Entity
     *
     * @param target
     * @param edited
     */
    public void setEntity(Entity target, Entity edited) {
        if (!target.getClass().equals(edited.getClass())) {
            return; // throw error.
        }
        if (target instanceof Item) {
            items.setEntity((Item) target, (Item) edited);
        } else if (target instanceof Character) {
            characters.setEntity((Character) target, (Character) edited);
        } else if (target instanceof Mob) {
            mobs.setEntity((Mob) target, (Mob) edited);
        } else {

        }
    }

    /**
     * Edit entity
     *
     * @param key
     */
    public void deleteEntity(Entity key) {
        if (key instanceof Item) {
            items.deleteEntity((Item) key);
        } else if (key instanceof Character) {
            characters.deleteEntity((Character) key);
        } else if (key instanceof Mob) {
            mobs.deleteEntity((Mob) key);
        } else {

        }
    }

    // Misc ====================

    /**
     * Return some list for the updated list
     *
     * @return
     */
    public ObservableList<? extends Entity> getList() {
        // not slap at all
        return characters.entities.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return "Characters:" + characters.toString() + "\nItems" + items.toString() + "\nMobs" + mobs.toString();
    }
}
