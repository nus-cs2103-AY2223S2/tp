package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Pattern;

/**
 * Represents a GroupTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class GroupTag extends Tag implements Comparable<GroupTag> {

    public static final String MESSAGE_CONSTRAINTS = "The names of group tags must be composed of letters or numbers, "
            + "with a maximum length of 20 characters, and cannot be left blank.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}{1,20}";

    /**
     * Constructs a {@code GroupTag}.
     *
     * @param tagName A valid tag name.
     */
    public GroupTag(String tagName) {
        super(tagName);
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    boolean isValidTagName(String test, String regex) {
        return Pattern.matches(VALIDATION_REGEX, test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupTag // instanceof handles nulls
                && tagName.equals(((GroupTag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName;
    }

    @Override
    public int compareTo(GroupTag otherGroupTag) {
        return tagName.compareTo(otherGroupTag.tagName);
    }

}
