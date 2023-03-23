package seedu.vms.model.keyword;

/**
 * Represents an AppointmentKeyword in the vaccine management system.
 */
public class AppointmentKeyword extends Keyword {
    private static final String MAIN_KEY_NAME = "appointment";

    public AppointmentKeyword(String keyword) {
        super(keyword, MAIN_KEY_NAME);
    }

}
