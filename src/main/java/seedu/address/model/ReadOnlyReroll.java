package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Unmodifiable view of Reroll.
 */
public interface ReadOnlyReroll {
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

    /**
     * Returns an unmodifiable view of all entities.
     */
    ReadOnlyEntities getEntities();

    /**
     * Returns list of template names.
     */
    List<String> getTemplates();
}
