package seedu.modtrek.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tagged Requirement in the Curriculum
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final int NUM_TAGS = 6;

    public static final String MESSAGE_CONSTRAINTS = "Tag name should be one of possible curriculum requirements.";

    public static final String MESSAGE_MISSING_DETAIL = "Missing tag after /t.";

    public final String tagName;


    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid requirement
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = ValidTag.getShortForm(tagName).toString();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        try {
            String formattedString = test
                    .replace(" ", "_")
                    .toUpperCase();
            ValidTag.valueOf(formattedString);
            return true;
        } catch (IllegalArgumentException e) {
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
        return ValidTag.getLongForm(tagName).toString().replace("_", " ");
    }

    public String getShortForm() {
        return ValidTag.getShortForm(tagName).toString();
    }
}
