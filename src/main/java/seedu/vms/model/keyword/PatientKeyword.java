package seedu.vms.model.keyword;

public class PatientKeyword implements Keyword {
    private static final String MAIN_KEY_NAME = "patient";

    private final String keyword;

    public PatientKeyword(String keyword) {
        this.keyword = keyword;
    }

}
