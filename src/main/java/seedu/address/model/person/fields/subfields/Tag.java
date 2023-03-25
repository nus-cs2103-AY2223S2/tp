package seedu.address.model.person.fields.subfields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.fields.Field;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";


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
