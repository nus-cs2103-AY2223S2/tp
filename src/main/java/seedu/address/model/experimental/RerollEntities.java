package seedu.address.model.experimental;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

import java.util.List;

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
        this.entities.setPersons(entities);
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

        entities.setPerson(target, edited);
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
