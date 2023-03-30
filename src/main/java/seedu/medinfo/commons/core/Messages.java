package seedu.medinfo.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_INVALID_WARD_DISPLAYED_INDEX = "The ward index provided is invalid";
    public static final String MESSAGE_DELETE_WAITING_ROOM = "The Waiting Room cannot be deleted";
    public static final String MESSAGE_ABORT_DELETE = "Deletion cancelled";

    public static final String MESSAGE_DELETE_WARD_WITH_PATIENTS =
            "The ward cannot be deleted as there are patients inside";

    public static final String MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW = "All %1$d patients listed!";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d out of %2$d patients listed!";

}
