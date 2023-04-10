package seedu.address.model.person.fields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's major in the address book.
 */
public class Major extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Major should only contain alphabets and spaces";
    public static final String VALIDATION_REGEX = "^[A-Za-z\\s]+$";

    /**
     * Constructs a {@code Major}.
     *
     * @param majorName A valid major name.
     */
    public Major(String majorName) {
        super(majorName);
        checkArgument(isValidMajor(majorName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns if a given string is a valid major name.
     */
    public static boolean isValidMajor(String trimmedMajor) {
        if (Objects.equals(trimmedMajor, "")) {
            return true;
        }
        return trimmedMajor.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && this.value.equals(((Major) other).value)); // state check
    }

}
