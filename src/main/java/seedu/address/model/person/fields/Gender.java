package seedu.address.model.person.fields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's gender in the address book.
 */
public class Gender extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Gender must be alphanumeric";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+$";

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid name.
     */
    public Gender(String gender) {
        super(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns if a given string is a valid gender.
     */
    public static boolean isValidGender(String trimmedGender) {
        if (Objects.equals(trimmedGender, "")) {
            return true;
        }
        return trimmedGender.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && this.value.equals(((Gender) other).value)); // state check
    }

}
