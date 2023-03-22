package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Reroll...
 */
public class Reroll implements ReadOnlyReroll {

    private final RerollAllEntities entities;
    private final RerollCharacters characters;
    private final RerollItems items;
    private final RerollMobs mobs;

    {
        entities = new RerollAllEntities();
        characters = new RerollCharacters(entities.getCharacters());
        items = new RerollItems(entities.getItems());
        mobs = new RerollMobs(entities.getMobs());
    }

    public Reroll() {}

    /**
     * Create Reroll from existing copy
     * @param toBeCopied
     */
    public Reroll(ReadOnlyReroll toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Reset data to newData
     * @param newData
     */
    public void resetData(ReadOnlyReroll newData) {
        requireNonNull(newData);

        // Initialize all entities
        entities.resetData(newData.getEntities());
    }

    @Override
    public ReadOnlyEntities getEntities() {
        return entities;
    }

    @Override
    public ReadOnlyEntities getItems() {
        return items;
    }

    @Override
    public ReadOnlyEntities getCharacters() {
        return characters;
    }

    @Override
    public ReadOnlyEntities getMobs() {
        return mobs;
    }

    // Entity level operations ===============

    /**
     * Has entity
     * @param e
     * @return
     */
    public boolean hasEntity(Entity e) {
        return entities.hasEntity(e);
    }

    /**
     * Add Entity
     */
    public void addEntity(Entity entity) {
        entities.addEntity(entity);
    }

    /**
     * Set entity
     * @param target
     * @param edited
     */
    public void setEntity(Entity target, Entity edited) {
        entities.setEntity(target, edited);
    }

    /**
     * Delete entity
     * @param entity
     */
    public void deleteEntity(Entity entity) {
        entities.deleteEntity(entity);
    }


    // Misc ====================

    /**
     * Return list for the all entities in
     * @return
     */
    public ObservableList<Entity> getAllList() {
        return entities.getEntityList();
    }

    @Override
    public String toString() {
        return "Many entities...";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Reroll
                && entities.equals(((Reroll) other).entities));
    }
}
