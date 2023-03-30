package seedu.vms.model.keyword;

/**
 * Represents an Keyword in the vaccine management system.
 */
public class Keyword {
    public static final String MAIN_APPOINTMENT_STRING = "appointment";
    public static final String MAIN_PATIENT_STRING = "patient";
    public static final String MAIN_VACCINATION_STRING = "vaccination";

    private final String keyword;
    private final String mainKeyword;

    /**
     * Both fields must be present and not null.
     */
    public Keyword(String mainKeyword, String keyword) {
        this.keyword = keyword;
        this.mainKeyword = mainKeyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getMainKeyword() {
        return this.mainKeyword;
    }

    /**
     * Method to check if the main keyword is valid.
     *
     * @param word - the word to check.
     * @return {@code true} if the patient meets the requirements and
     *      {@code false} otherwise.
     */
    public static boolean isValidMainKeyword(String word) {
        assert word != null;

        switch (word) {
        case (MAIN_PATIENT_STRING):
            return true;

        case (MAIN_APPOINTMENT_STRING):
            return true;

        case (MAIN_VACCINATION_STRING):
            return true;

        default:
            return false;
        }
    }

    public String toString() {
        return keyword + "-->" + mainKeyword;
    }
}
