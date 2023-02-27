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
}
