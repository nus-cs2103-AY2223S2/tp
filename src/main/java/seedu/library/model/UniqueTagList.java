package seedu.library.model;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.library.model.bookmark.exceptions.BookmarkNotFoundException;
import seedu.library.model.bookmark.exceptions.DuplicateBookmarkException;
import seedu.library.model.tag.Tag;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#isSameTag(Tag)}.
 * As such, adding and updating of Tags uses Tag#isSameTag(Tag) for equality so as to
 * ensure that the Tag being added or updated is unique in terms of identity in the UniqueTagList.
 * However, the removal of a tag uses Tag#equals(Object) so as to ensure that
 * the Tag with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tag#isSameTag(Tag)
 */
public class UniqueTagList implements Iterable<Tag> {

    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTag);
    }

    /**
     * Returns true if all the tags in toCheck are in the tag list.
     * @param toCheck Set of tags to check.
     * @return true if all the tags in toCheck are in the tag list.
     */
    public boolean containsAll(Set<Tag> toCheck) {
        requireNonNull(toCheck);
        return internalList.containsAll(toCheck);
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBookmarkException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the list.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookmarkNotFoundException();
        }

        if (!target.isSameTag(editedTag) && contains(editedTag)) {
            throw new DuplicateBookmarkException();
        }

        internalList.set(index, editedTag);
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.removeIf(tagName -> tagName.isSameTag(toRemove))) {
            throw new BookmarkNotFoundException();
        }
    }


    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tag} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateBookmarkException();
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
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).isSameTag(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    public String tagListToString() {
        return internalUnmodifiableList.toString();
    }
}

