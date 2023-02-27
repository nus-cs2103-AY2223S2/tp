package seedu.address.model.person.fields;

public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Major";
    public String majorName;

    public Major(String majorName) {
        this.majorName = majorName;
    }

    public static boolean isValidMajor(String trimmedMajor) {
        return true;
    }
}
