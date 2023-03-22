package seedu.vms.model.keyword;

public class VaccinationKeyword implements Keyword {
    private static final String MAIN_KEY_NAME = "appointment";

    private final String keyword;

    public VaccinationKeyword(String keyword) {
        this.keyword = keyword;
    }

}