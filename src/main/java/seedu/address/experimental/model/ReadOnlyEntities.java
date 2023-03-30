package seedu.address.experimental.model;

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
}
