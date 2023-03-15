package seedu.address.model.experimental;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;


/**
 * TBC
 * @param <T> TBC
 */
public abstract class RerollEntities<T extends Entity> implements ReadOnlyEntities {

    /**
     * TBC
     */
    protected final UniqueEntityList<T> entities;
    {
        entities = new UniqueEntityList<>();
    }

    /**
     * TBC
     */
    void setEntities(List<T> entities) {
        this.entities.setPersons(entities);
    }

    /**
     * TBC
     */
    void resetData(ReadOnlyEntities<T> newData) {
        requireNonNull(newData);

        setEntities(newData.getEntityList());
    }

    // Entity level operations

    /**
     * TBC
     */
    boolean hasEntity(T entity) {
        requireNonNull(entity);
        return entities.contains(entity);
    }

    /**
     * TBC
     */
    void addEntity(T entity) {
        requireNonNull(entity);
        entities.add(entity);
    }

    /**
     * TBC
     */
    void setEntity(T target, T edited) {
        requireNonNull(edited);

        entities.setPerson(target, edited);
    }

    /**
     * TBC
     */
    void deleteEntity(T entity) {
        entities.remove(entity);
    }

    /**
     * TBC
     */
    @Override
    public ObservableList<T> getEntityList() {
        return entities.asUnmodifiableObservableList();
    }

    /**
     * TBC
     */
    @Override
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " entities";
    }
}
