package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the master deck.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    /**
     * An enumeration of tag names for Tag class to show the difficulty level of a card.
     */
    public enum TagName {
        UNTAGGED,
        EASY,
        MEDIUM,
        HARD
    }

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be either Easy, Medium, or Hard";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final TagName tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = TagName.valueOf(tagName.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        requireNonNull(test);
        try {
            TagName.valueOf(test.toUpperCase());
            return test.matches(VALIDATION_REGEX);
        } catch (Exception e) {
            return false;
        }
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
        return '[' + tagName.name() + ']';
    }

}
