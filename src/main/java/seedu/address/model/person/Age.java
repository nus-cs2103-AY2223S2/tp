package seedu.address.model.person;

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
        this.age = age;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAge(String age) {
        if (age == "") {
            return true;
        }
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
    public int hashCode() {
        return age.hashCode();
    }
}
