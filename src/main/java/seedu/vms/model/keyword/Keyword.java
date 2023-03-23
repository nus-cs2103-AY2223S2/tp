package seedu.vms.model.keyword;

public class Keyword {
    private final String keyword;
    private final String mainKeyword;

    private static final String MAIN_PATIENT_STRING = "patient";
    private static final String MAIN_APPOINTMENT_STRING = "appointment";
    private static final String MAIN_VACCINATION_STRING = "vaccination";


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

    public static boolean isValidMainKeyword(String word) {
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
}