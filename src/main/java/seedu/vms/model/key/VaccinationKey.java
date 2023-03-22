package seedu.vms.model.key;

public class VaccinationKey implements Key {
    private static final String MAIN_KEY_NAME = "appointment";

    private final String keyword;

    public VaccinationKey(String keyword) {
        this.keyword = keyword;
    }

}