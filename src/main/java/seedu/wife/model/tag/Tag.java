package seedu.wife.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag in the WIFE.
 * Guarantees: immutable; name is valid as declared in {@link seedu.wife.model.tag.TagName#isValidTagName(String)}
 */
public class Tag {
    public final TagName tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = new TagName(tagName);
    }

    /**
     * Returns the tag name of the {@code Tag}
     * @return
     */
    public String getTagName() {
        return this.tagName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName.toString() + ']';
    }

}
