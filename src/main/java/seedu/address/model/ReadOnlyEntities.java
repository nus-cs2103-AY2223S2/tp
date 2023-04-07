package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Unmodifiable view of an entity list
 */
public interface ReadOnlyEntities {
    /**
     * Returns an unmodifiable view of the entity list.
     * The list contains entities of a specific class or all classes.
     * The list will not contain duplicate items.
     */
    ObservableList<Entity> getEntityList();

    /**
     * Returns an unmodifiable view of all items.
     */
    ObservableList<Entity> getItemList();

    /**
     * Returns an unmodifiable view of all characters.
     */
    ObservableList<Entity> getCharList();

    /**
     * Returns an unmodifiable view of all mobs.
     */
    ObservableList<Entity> getMobList();


}
