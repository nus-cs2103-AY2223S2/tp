package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's age in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(String)}
 */
public class Age {
    public static final String MESSAGE_CONSTRAINTS =
            "Person's age should be integer (non-integer not allowed for this version)";

    // treat age also as a string
    private String age;

    /**
     * Constructs a {@code Name}.
     *
     * @param age A valid age.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        this.age = age;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAge(String age) {
        if (age == "") {
            return true;
        }
        requireNonNull(age);
        // check if valid age
        try {
            Integer.parseInt(age);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public String getAge() {
        return age;
    }
    @Override
    public String toString() {
        return age == "" ? "" : "(age: " + age + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Age // instanceof handles nulls
                && age.equals(((Age) other).age)); // state check
    }
    @Override
    public int hashCode() {
        return age.hashCode();
    }
}
