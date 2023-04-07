package seedu.address.model;

import java.util.List;

/**
 * Unmodifiable view of Reroll.
 */
public interface ReadOnlyReroll {
    /**
     * Returns an unmodifiable view of all entities.
     */
    ReadOnlyEntities getEntities();

    /**
     * Returns list of template names.
     */
    List<String> getTemplates();
}
