package seedu.address.model.person.fields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
/**
 * Represents a Person's faculty in the address book.
 */
public class Faculty extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Faculty should only contain alphabets and spaces";
    public static final String VALIDATION_REGEX = "^[A-Za-z\\s]+$";

    /**
     * Constructs a {@code Faculty}.
     * @param faculty A valid Faculty.
     */
    public Faculty(String faculty) {
        super(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns if a given string is a valid faculty.
     */
    public static boolean isValidFaculty(String trimmedFaculty) {
        if (Objects.equals(trimmedFaculty, "")) {
            return true;
        }
        return trimmedFaculty.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && this.value.equals(((Faculty) other).value)); // state check
    }
}
