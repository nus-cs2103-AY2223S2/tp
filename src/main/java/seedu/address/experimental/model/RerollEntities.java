package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

/**
 * Abstraction of some specific entity class T
 * @param <T>
 */
public abstract class RerollEntities<T extends Entity> implements ReadOnlyEntities {

    protected final UniqueEntityList<T> entities;
    {
        entities = new UniqueEntityList<>();
    }
    // List level operations ==============================
    void setEntities(List<T> entities) {
        this.entities.setEntities(entities);
    }

    void resetData(ReadOnlyEntities<T> newData) {
        requireNonNull(newData);

        setEntities(newData.getEntityList());
    }

    // Entity level operations ===============================

    boolean hasEntity(T entity) {
        requireNonNull(entity);
        return entities.contains(entity);
    }

    void addEntity(T entity) {
        requireNonNull(entity);
        entities.add(entity);
    }

    void setEntity(T target, T edited) {
        requireNonNull(edited);

        entities.setEntity(target, edited);
    }

    void deleteEntity(T entity) {
        entities.remove(entity);
    }

    @Override
    public ObservableList<T> getEntityList() {
        return entities.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " entities";
    }
}
