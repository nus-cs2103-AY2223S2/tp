package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_ALPHANUMERIC_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String MESSAGE_LENGTH_CONSTRAINTS = "Tags names should be at most 60 characters long";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagLength(tagName), MESSAGE_LENGTH_CONSTRAINTS);
        checkArgument(isValidTagName(tagName), MESSAGE_ALPHANUMERIC_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Checks if a given tag is at most 60 characters long.
     *
     * @param test Tag to test.
     * @return true if the tag is of a valid length, false otherwise.
     */
    public static boolean isValidTagLength(String test) {
        return test.length() <= 60;
    }

    /**
     * Checks if a given tag has a valid name.
     *
     * @param test Tag to test.
     * @return true if the tag has a valid name, false otherwise.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
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
        return '[' + tagName + ']';
    }

}
