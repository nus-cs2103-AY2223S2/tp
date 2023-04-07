package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.exceptions.DuplicateEntityException;
import seedu.address.model.entity.exceptions.EntityNotFoundException;

/**
 * A list of entities that enforces uniqueness between its elements and does not allow nulls.
 * A entity is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * entities uses Entity#isSameEntity(Entity) for equality so as to ensure that the entity being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a entity uses Entity#equals(Object) so
 * as to ensure that the entity with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Entity#isSameEntity(Entity)
 */
public class UniqueEntityList implements Iterable<Entity> {

    private final ObservableList<Entity> internalList = FXCollections.observableArrayList();
    private final ObservableList<Entity> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent entity as the given argument.
     */
    public boolean contains(Entity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntity);
    }

    /**
     * Adds a entity to the list.
     * The entity must not already exist in the list.
     */
    public void add(Entity toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEntityException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the entity {@code target} in the list with {@code editedEntity}.
     * {@code target} must exist in the list.
     * The entity identity of {@code editedEntity} must not be the same as another existing entity in the list.
     */
    public void setEntity(Entity target, Entity editedEntity) {
        requireAllNonNull(target, editedEntity);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntityNotFoundException();
        }

        if (!target.isSameEntity(editedEntity) && contains(editedEntity)) {
            throw new DuplicateEntityException();
        }

        internalList.set(index, editedEntity);
    }

    /**
     * Removes the equivalent entity from the list.
     * The entity must exist in the list.
     */
    public void remove(Entity toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code entities}.
     * {@code entities} must not contain duplicate entities.
     */
    public void setEntities(List<Entity> entities) {
        requireAllNonNull(entities);
        if (!entitiesAreUnique(entities)) {
            throw new DuplicateEntityException();
        }

        internalList.setAll(entities);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Entity> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Entity> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntityList // instanceof handles nulls
                        && internalList.equals(((UniqueEntityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entities} contains only unique entities.
     */
    private boolean entitiesAreUnique(List<? extends Entity> entities) {
        for (int i = 0; i < entities.size() - 1; i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(i).isSameEntity(entities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
