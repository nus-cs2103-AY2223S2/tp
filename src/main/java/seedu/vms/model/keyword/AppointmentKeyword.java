package seedu.vms.model.keyword;

public class AppointmentKeyword implements Keyword {
    private static final String MAIN_KEY_NAME = "appointment";

    private final String keyword;

    public AppointmentKeyword(String keyword) {
        this.keyword = keyword;
    }

}