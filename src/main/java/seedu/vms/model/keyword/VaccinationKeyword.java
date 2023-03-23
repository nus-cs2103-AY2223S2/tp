package seedu.vms.model.keyword;

/**
 * Represents a VaccinationKeyword in the vaccine management system.
 */
public class VaccinationKeyword extends Keyword {
    private static final String MAIN_KEY_NAME = "vaccination";

    public VaccinationKeyword(String keyword) {
        super(keyword, MAIN_KEY_NAME);
    }

}
