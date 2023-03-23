package seedu.vms.model.keyword;

/**
 * Represents an PatientKeyword in the vaccine management system.
 */
public class PatientKeyword extends Keyword {
    private static final String MAIN_KEY_NAME = "patient";

    public PatientKeyword(String keyword) {
        super(keyword, MAIN_KEY_NAME);
    }

}
