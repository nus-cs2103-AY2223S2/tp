package seedu.vms.model.keyword;

import java.util.Objects;

/**
 * Represents an Keyword in the vaccine management system.
 */
public class Keyword {
    public static final String MAIN_APPOINTMENT_STRING = "appointment";
    public static final String MAIN_BASIC_STRING = "basic";
    public static final String MAIN_EXIT_STRING = "exit";
    public static final String MAIN_HELP_STRING = "help";
    public static final String MAIN_KEYWORD_STRING = "keyword";
    public static final String MAIN_PATIENT_STRING = "patient";
    public static final String MAIN_VACCINATION_STRING = "vaccination";

    private final String keyword;
    private final String mainKeyword;

    /**
     * Both fields must be present and not null.
     */
    public Keyword(String mainKeyword, String keyword) {
        this.keyword = Objects.requireNonNull(keyword);
        this.mainKeyword = Objects.requireNonNull(mainKeyword);
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
     * @return {@code true} if the keyword meets the requirements and
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

    /**
     * Method to check if the keyword is not a main keyword.
     *
     * @param word - the word to check.
     * @return {@code true} if the keyword does not meet the requirements and
     *      {@code false} otherwise.
     */
    public static boolean isNotMainKeyword(String word) {
        assert word != null;

        switch (word) {
        case (MAIN_PATIENT_STRING):
            return false;

        case (MAIN_APPOINTMENT_STRING):
            return false;

        case (MAIN_VACCINATION_STRING):
            return false;

        case (MAIN_BASIC_STRING):
            return false;

        case (MAIN_EXIT_STRING):
            return false;

        case (MAIN_HELP_STRING):
            return false;

        case (MAIN_KEYWORD_STRING):
            return false;

        default:
            return true;
        }
    }

    public String toString() {
        return keyword + "-->" + mainKeyword;
    }
}
