package seedu.address.model.person.fields.subfields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.fields.Field;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric and space";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static final int MAX_VALUE_LENGTH = 16;


    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        super(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Truncates the value of this {@code Tag} if it is longer than {@code MAX_VALUE_LENGTH = 16}.
     * Since most English words are shorter than 10 letters and {@code Tag}s are single words,
     * a 16-letter limit should be enough.
     * This method is used in displaying the {@code Tag}s in a {@code FlowPane}
     * so that tags that are too long do not overflow the width of the parent.
     */
    public String truncateValue() {
        return value.length() > MAX_VALUE_LENGTH
                ? value.substring(0, MAX_VALUE_LENGTH) + "..."
                : value;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.value.equals(((Tag) other).value)); // state check
    }

    @Override
    public String toString() {
        return '[' + this.value + ']';
    }

}
