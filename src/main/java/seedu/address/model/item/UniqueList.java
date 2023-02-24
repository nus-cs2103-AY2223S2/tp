package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.exceptions.ItemDuplicateException;
import seedu.address.model.item.exceptions.ItemNotFoundException;

/**
 * The class that resembles a list of items that are unique.
 *
 * @param <T> the type of item in the list. They must implement the {@code
 *            Identifiable} interface so that they can be identified by their
 *            unique ID.
 */
public class UniqueList<T extends Identifiable> implements Iterable<T> {

    /**
     * The internal list responsible for holding the items.
     */
    private final ObservableList<T> internalList;

    /**
     * The unmodifiable view of the {@code internalList}.
     */
    private final ObservableList<T> internalUnmodifiableList;

    /**
     * Creates a {@code UniqueList} from the given list of items. Please make
     * sure that the items in the list are unique.
     *
     * @param internalList the list of items out of which the {@code UniqueList}
     *                     will be created.
     */
    private UniqueList(ObservableList<T> internalList) {
        this.internalList = internalList;
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Creates an empty {@code UniqueList}.
     */
    public UniqueList() {
        this(FXCollections.observableArrayList());
    }

    /**
     * Creates a {@code UniqueList} from the given list of items. Please make
     * sure that the items in the list are unique.
     *
     * @param internalList the list of items out of which the {@code UniqueList}
     *                     will be created.
     * @param <T>          the type of item in the list. They must implement the
     *                     {@code Identifiable} interface so that they can be
     *                     identified by their unique ID.
     * @return a {@code UniqueList} from the given list of items.
     */
    public static <T extends Identifiable> UniqueList<T> fromObservableList(ObservableList<T> internalList) {
        requireNonNull(internalList);
        requireAllNonNull(internalList.toArray());
        if (!internalList.isEmpty() && itemsHaveDuplicate(internalList)) {
            throw new ItemDuplicateException(internalList.get(0).getClass());
        }
        return new UniqueList<>(internalList);
    }

    /**
     * Returns true if the list contains an equivalent {@code T} as the given
     * argument.
     *
     * @return true if the list contains an equivalent {@code T} as the given
     *         argument.
     */
    public boolean contains(T toCheck) {
        return this.contains(toCheck.getId());
    }

    /**
     * Returns true if the list contains an item with the given ID.
     *
     * @param id the ID to check
     * @return true if the list contains an item with the given ID.
     */
    public boolean contains(String id) {
        requireNonNull(id);
        return internalList.stream().anyMatch((val) -> val.getId().equals(id));
    }

    /**
     * Adds a {@code T} to the list.
     * The {@code T} must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new ItemDuplicateException(toAdd.getClass());
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the {@code T} {@code target} in the list with {@code editedItem}.
     *
     * @param target     the {@code T} to be replaced.
     * @param editedItem the {@code T} to replace the target with.
     */
    public void setItem(T target, T editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);

        if (index == -1) {
            throw new ItemNotFoundException(target);
        }

        if (!Identifiable.isSame(target, editedItem) && contains(editedItem)) {
            throw new ItemDuplicateException(editedItem.getClass());
        }

        internalList.set(index, editedItem);
    }

    /**
     * Removes the equivalent {@code T} from the list.
     * The {@code T} must exist in the list.
     */
    public void remove(T toRemove) {
        this.remove(toRemove.getId());
    }

    /**
     * Removes the item with the given ID from the list.
     * The item with the given ID must exist in the list.
     */
    public void remove(String id) {
        requireNonNull(id);
        if (!internalList.removeIf((val) -> val.getId().equals(id))) {
            throw new ItemNotFoundException(id);
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     *
     * @param replacement the replacement list.
     */
    public void setItems(List<T> replacement) {
        requireNonNull(replacement);
        if (UniqueList.itemsHaveDuplicate(replacement)) {
            throw new ItemDuplicateException(replacement.get(0).getClass());
        }
        internalList.setAll(replacement);
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate items.
     *
     * @param replacement the replacement list.
     */
    public void setItems(UniqueList<T> replacement) {
        setItems(replacement.internalList);
    }

    /**
     * The size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UniqueList<?>)) {
            return false;
        }
        UniqueList<?> otherList = (UniqueList<?>) other;
        return internalList.equals(otherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code items} contains only unique items.
     *
     * @param items the list of items to be checked.
     */
    public static <T extends Identifiable> boolean itemsHaveDuplicate(List<T> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (Identifiable.isSame(items.get(i), items.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }
}
