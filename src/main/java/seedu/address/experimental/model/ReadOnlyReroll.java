package seedu.address.experimental.model;

import java.util.List;

/**
 * Unmodifiable view of Reroll.
 */
public interface ReadOnlyReroll {
    /**
     * Returns an unmodifiable view of all items.
     */
    ReadOnlyEntities getItems();

    /**
     * Returns an unmodifiable view of all characters.
     */
    ReadOnlyEntities getCharacters();

    /**
     * Returns an unmodifiable view of all mobs.
     */
    ReadOnlyEntities getMobs();

    /**
     * Returns an unmodifiable view of all entities.
     */
    ReadOnlyEntities getEntities();

    /**
     * Returns list of template names.
     */
    List<String> getTemplates();
}
