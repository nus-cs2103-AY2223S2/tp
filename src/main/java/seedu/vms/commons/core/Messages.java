package seedu.vms.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_MISSING_PARAMETER_FORMAT = "Missing parameters! \n%1$s";
    public static final String MESSAGE_INVALID_PATIENT_ID = "The PATIENT_ID provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX = "The appointment index provided is "
            + "invalid";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_APPOINTMENTS_LISTED_OVERVIEW = "%1$d appointments listed!";
    public static final String MESSAGE_VACCINATION_LISTED_OVERVIEW = "%d vaccinations listed!";
    public static final String MESSAGE_INVALID_KEYWORD_DISPLAYED_INDEX = "The keyword index provided is invalid";


    public static final String MESSAGE_EXISTING_KEYWORD_EXISTS = "There is an existing keyword mapping for %s! "
            + "Please delete before creating a new keyword mapping with %s. \n";
    public static final String MESSAGE_INVALID_MAIN_KEYWORD = "Invalid main keyword! "
            + "Only %s, %s, and %s are allowed. \n";
    public static final String MESSAGE_INVALID_SUB_KEYWORD = "Invalid new keyword! %s, %s, %s, %s, %s, %s,"
            + "and %s are NOT allowed. \n";
    public static final String MESSAGE_KEYWORD_DOES_NOT_EXIST = "There is not a existing "
            + "keyword mapping for %s!";
    public static final String MESSAGE_KEYWORD_IS_EMPTY = "New keyword cannot be empty. Please try again.";

    public static final String FORMAT_UNEXPECTED_APPOINTMENT_CHANGE = "Unexpected change detected!\n"
            + "The following appointments will become invalid if the change were to happen:%s";
    public static final String MESSAGE_USE_FORCE = "Add [--force true] to the command to force the change";

    public static final String FORMAT_LIMIT_EX = "Limit of %d reached";
}
