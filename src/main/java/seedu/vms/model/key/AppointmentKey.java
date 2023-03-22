package seedu.vms.model.key;

public class AppointmentKey implements Key {
    private static final String MAIN_KEY_NAME = "appointment";

    private final String keyword;

    public AppointmentKey(String keyword) {
        this.keyword = keyword;
    }

}