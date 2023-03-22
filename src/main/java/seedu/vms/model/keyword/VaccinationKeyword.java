package seedu.vms.model.keyword;

public class VaccinationKeyword extends Keyword {
    private static final String MAIN_KEY_NAME = "appointment";

    public VaccinationKeyword(String keyword) {
        super(keyword, MAIN_KEY_NAME);
    }

}