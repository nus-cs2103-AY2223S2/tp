package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.student.ParentName;

/**
 * Represents the Image of a Student object.
 */
public class Image {
    public static final String MESSAGE_CONSTRAINTS = "Image must be file destination";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\S+";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param image A valid student image file destination.
     */
    public Image(String image) {
        requireNonNull(image);
        checkArgument(isValidImage(image), MESSAGE_CONSTRAINTS);
        value = image;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidImage(String test) {
        if (test.equals("Insert student image here!") || test.equals("Insert parent image here!")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentName// instanceof handles nulls
                && value.equals(((ParentName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
