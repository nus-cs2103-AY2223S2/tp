package seedu.wife.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.wife.model.tag.exceptions.DuplicateTagException;
import seedu.wife.model.tag.exceptions.TagNotFoundException;
import seedu.wife.model.tag.exceptions.TagStorageEmptyException;
import seedu.wife.model.tag.exceptions.TagStorageFullException;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#isSameTag(Tag)}. As such, adding and updating of
 * tags uses Tag#isSameTag(Tag) for equality so as to ensure that the tag being added or updated is
 * unique in terms of identity in the UniqueTagList. However, the removal of a tag uses Tag#equals(Object) so
 * as to ensure that the tag with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tag#equals(Object)
 */
public class UniqueTagList implements Iterable<Tag> {

    public static final Integer TAG_LIST_MAX_SIZE = 50;
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        if (isStorageFull()) {
            throw new TagStorageFullException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns true if the storage is full, else false.
     */
    public boolean isStorageFull() {
        return internalList.size() == TAG_LIST_MAX_SIZE;
    }

    /**
     * Replaces the tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the list.
     * The tag of {@code editedTag} must not be the same as another existing tag in the list.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TagNotFoundException();
        }

        if (!target.equals(editedTag) && contains(editedTag)) {
            throw new DuplicateTagException();
        }

        internalList.set(index, editedTag);
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);

        if (internalList.size() == 0) {
            throw new TagStorageEmptyException();
        }

        if (!internalList.remove(toRemove)) {
            throw new TagNotFoundException();
        }

    }

    /**
     * Replace the content of this list with new {@code UniqueTagList}
     */
    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tag.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                && internalList.equals(((UniqueTagList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(List<Tag> tags) {
        HashSet<Tag> set = new HashSet<>();

        for (Tag tag : tags) {
            if (set.contains(tag)) {
                return false;
            }
            set.add(tag);
        }
        return true;
    }
}
