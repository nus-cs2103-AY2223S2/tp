package seedu.address.experimental.model;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Abstraction of all Mobs
 */
public class RerollMobs implements ReadOnlyEntities {

    private final ObservableList<Entity> mobs;

    /**
     * Initialize with list of mobs.
     * @param mobs Unmodifiable list of mobs.
     */
    public RerollMobs(ObservableList<Entity> mobs) {
        this.mobs = mobs;
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return mobs;
    }

    @Override
    public Entity getEntityWithName(String name) {
        Entity toReturn = null;
        for (Entity entity : mobs) {
            if (entity.getName().fullName.equals(name)) {
                toReturn = entity;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollMobs
                && mobs.equals(((RerollMobs) other).mobs));
    }
}
