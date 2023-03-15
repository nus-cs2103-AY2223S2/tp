package seedu.address.model.experimental;

import javafx.collections.ObservableList;

import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

import static java.util.Objects.requireNonNull;

// Driver function.
public class Reroll implements ReadOnlyReroll {
    private final RerollCharacters characters;
    private final RerollItems items;
    private final RerollMobs mobs;

    {
        characters = new RerollCharacters();
        items = new RerollItems();
        mobs = new RerollMobs();
    }

    /**
     * TBC
     */
    public Reroll() {}

    /**
     * TBC
     * @param toBeCopied TBC
     */
    public Reroll(ReadOnlyReroll toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * TBC
     * @param newData TBC
     */
    public void resetData(ReadOnlyReroll newData) {
        requireNonNull(newData);

        characters.resetData(newData.getCharacters());
        items.resetData(newData.getItems());
        mobs.resetData(newData.getMobs());
    }

    /**
     * TBC
     * @return TBC
     */
    @Override
    public ReadOnlyEntities<Item> getItems() {
        return items;
    }

    /**
     * TBC
     * @return TBC
     */
    @Override
    public ReadOnlyEntities<Character> getCharacters() {
        return characters;
    }

    /**
     * TBC
     * @return TBC
     */
    @Override
    public ReadOnlyEntities<Mob> getMobs() { return mobs; }

    // Entity level operations ===============

    /**
     * TBC
     * @param e TBC
     * @return TBC
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
     * TBC
     * @param e TBC
     */
    public void addEntity(Entity e) {
        if (e instanceof Item) {
            items.addEntity((Item) e);
        } else if (e instanceof Character) {
            characters.addEntity((Character) e);
        } else if (e instanceof Mob) {
            mobs.addEntity((Mob) e);
        } else {

        }
    }

    /**
     * TBC
     * @param target TBC
     * @param edited TBC
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
     * TBC
     * @param key TBC
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
     * TBC
     * @return TBC
     */
    public ObservableList<? extends Entity> getList() {
        // not slap at all
        return characters.entities.asUnmodifiableObservableList();
    }
}
