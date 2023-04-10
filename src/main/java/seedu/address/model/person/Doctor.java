package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's attending Doctor in the patient records.
 * Guarantees: immutable; is valid as declared in {@link #isValidDoctor(String)}
 */
public class Doctor {

    public static final String MESSAGE_CONSTRAINTS =
            "Doctor names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String doctor;

    /**
     * Constructs a {@code Doctor}.
     *
     * @param doctor A valid name.
     */
    public Doctor(String doctor) {
        requireNonNull(doctor);
        checkArgument(isValidDoctor(doctor), MESSAGE_CONSTRAINTS);
        this.doctor = doctor;
    }

    /**
     * Returns true if a given string is a valid doctor name.
     */
    public static boolean isValidDoctor(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return doctor;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Doctor // instanceof handles nulls
                && doctor.equals(((Doctor) other).doctor)); // state check
    }

    @Override
    public int hashCode() {
        return doctor.hashCode();
    }

}
