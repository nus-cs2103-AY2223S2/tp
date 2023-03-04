package seedu.address.model.person.fields;

/**
 * Represents a Person's major in the address book.
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Major";
    public final String majorName;

    public Major(String majorName) {
        this.majorName = majorName;
    }

    public static boolean isValidMajor(String trimmedMajor) {
        return true;
    }

    @Override
    public String toString() {
        return this.majorName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && this.majorName.equals(((Major) other).majorName)); // state check
    }

    @Override
    public int hashCode() {
        return this.majorName.hashCode();
    }
}
