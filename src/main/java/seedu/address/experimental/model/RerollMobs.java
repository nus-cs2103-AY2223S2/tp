package seedu.address.experimental.model;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Abstraction of all Mobs
 */
public class RerollMobs implements ReadOnlyEntities {

    // for convenience
    private final ObservableList<Entity> mobs;

    public RerollMobs(ObservableList<Entity> mobs) {
        this.mobs = mobs;
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return mobs;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollMobs
                && mobs.equals(((RerollMobs) other).mobs));
    }
}
