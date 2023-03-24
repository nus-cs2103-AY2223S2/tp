package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
/**
 * Represents a Person's faculty in the address book.
 */
public class Faculty {

    public static final String MESSAGE_CONSTRAINTS =
            "Faculty should only contain alphabets and spaces";
    public static final String VALIDATION_REGEX = "^[A-Za-z\\s]+$$";
    public final String faculty;

    /**
     * Constructs a {@code Faculty}.
     * @param faculty
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        this.faculty = faculty;
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
    public String toString() {
        return this.faculty;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && this.faculty.equals(((Faculty) other).faculty)); // state check
    }
    @Override
    public int hashCode() {
        return this.faculty.hashCode();
    }
}
