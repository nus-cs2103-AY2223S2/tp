package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric, do not include"
        + " characters like @,#,*,- or white space between words.";

    public static final String MESSAGE_TAG_LENGTH_ERROR = "Tag names should be between 1-20 characters.";

    public static final String MESSAGE_TAG_CONTENT_ERROR = "Tag names should not contain XXXXX as "
        + "an input or a substring of the input.";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTagName() {
        assert tagName != null : "Tag object with null tagName!";
        String[] splitTag = this.tagName.split("XXXXX");
        if (splitTag.length == 1) {
            return splitTag[0];
        } else {
            return splitTag[1];
        }
    }

    /**
     * @return the corresponding color code for css
     */
    public String tagColor() {
        return "#000080";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tag // instanceof handles nulls
            && tagName.equalsIgnoreCase(((Tag) other).tagName)); // state check
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
