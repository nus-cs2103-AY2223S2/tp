package seedu.address.experimental.model;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Abstraction of all templates
 */
public class RerollTemplates implements ReadOnlyEntities {

    private final ObservableList<Entity> templates;

    /**
     * Initialize with list of characters.
     * @param templates Unmodifiable view of templates.
     */
    public RerollTemplates(ObservableList<Entity> templates) {
        this.templates = templates;
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return templates;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollTemplates
                && templates.equals(((RerollTemplates) other).templates));
    }

}
