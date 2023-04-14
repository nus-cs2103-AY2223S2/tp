package seedu.library.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.library.model.tag.Tag;

/**
 * Personal tag list for users to tag bookmark
 * Duplicates are not allowed (by .isSameTag comparison)
 */
public class Tags implements ReadOnlyTags {
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tags = new UniqueTagList();
    }

    public Tags() {}

    /**
     * Creates a Tags using the tags in the {@code toBeCopied}
     */
    public Tags(ReadOnlyTags toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code Tags} with {@code newData}.
     */
    public void resetData(ReadOnlyTags newData) {
        requireNonNull(newData);

        setTags(newData.getTagList());
    }

    //// tag-level operations

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the tag list.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    public boolean containsAll(Set<Tag> tags) {
        return this.tags.containsAll(tags);
    }

    public boolean contains(Tag tag) {
        return this.tags.contains(tag);
    }

    /**
     * Adds a tag to the tag list.
     * The tag must not already exist in the tag list.
     */
    public void addTag(Tag b) {
        tags.add(b);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the tag list.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tags.setTag(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code Tags}.
     * {@code key} must exist in the tag list.
     */
    public void removeTag(Tag key) {
        tags.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tags.asUnmodifiableObservableList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tags // instanceof handles nulls
                && tags.equals(((Tags) other).tags));
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public String tagListToString() {
        return tags.tagListToString();
    }

}
