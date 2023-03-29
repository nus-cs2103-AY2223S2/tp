package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Wrap all data at the entity level.
 * Package-private utility class of Reroll.
 */
public class RerollAllEntities implements ReadOnlyEntities {

    private final UniqueEntityList entities;
    {
        entities = new UniqueEntityList();
    }

    // List level operations ==============================

    void setEntities(List<Entity> entities) {
        this.entities.setEntities(entities);
    }

    void resetData(ReadOnlyEntities newData) {
        requireNonNull(newData);

        setEntities(newData.getEntityList());
    }

    // Entity level operations ===============================
    boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        return entities.contains(entity);
    }

    void addEntity(Entity entity) {
        requireNonNull(entity);
        entities.add(entity);
    }

    void setEntity(Entity target, Entity edited) {
        requireNonNull(edited);

        entities.setEntity(target, edited);
    }

    void deleteEntity(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return entities.asUnmodifiableObservableList();
    }

    @Override
    public Entity getEntityWithName(String name) {
        Entity toReturn = null;
        for (Entity entity : entities) {
            if (entity.getName().fullName.equals(name)) {
                toReturn = entity;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " entities";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollAllEntities
                && entities.equals(((RerollAllEntities) other).entities));
    }
}
