package seedu.library.model;

import javafx.collections.ObservableList;
import seedu.library.model.tag.Tag;

/**
 * Unmodifiable view of tags
 */
public interface ReadOnlyTags {
    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();
}
