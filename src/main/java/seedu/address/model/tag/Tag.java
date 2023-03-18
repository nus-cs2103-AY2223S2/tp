package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag of a lecture.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag %1$s should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagDescription A valid tag name.
     */
    public Tag(String tagDescription) {
        requireNonNull(tagDescription);
        checkArgument(isValidTagName(tagDescription), MESSAGE_CONSTRAINTS);
        this.tagName = tagDescription;
    }

    /**
     * Return the name of a {@code Tag}
     * @return the tag name
     */

    public String getTagName() {
        return tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both tags have the same {@code tagName}.<p>
     * This defines a stronger notion of equality between two modules.
     *
     * @param other The module to check if it is equivalent to this module.
     * @return True if both modules have the same fields. Otherwise, false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return otherTag.getTagName().equals(this.tagName);
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
