package seedu.vms.model.key;

public class PatientKey implements Key {
    private static final String MAIN_KEY_NAME = "patient";

    private final String keyword;

    public PatientKey(String keyword) {
        this.keyword = keyword;
    }

}
