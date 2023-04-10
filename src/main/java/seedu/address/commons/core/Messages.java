package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n\n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE = "No person with name and phone found!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_DATETIME = "Invalid date and time provided";
    public static final String MESSAGE_INTERVIEW_DATETIME_NOT_REQUIRED = "Interview date and time is not required!";
    public static final String MESSAGE_INTERVIEW_DATETIME_IS_REQUIRED =
            "Please provide an interview date and time! (dd-MM-yyyy HH:mm)";
    public static final String MESSAGE_DUPLICATE_INTERVIEW_DATE =
            "There is a clash of interview date and time with %s!";
    public static final String MESSAGE_INTERVIEW_BEFORE_APPLICATION =
            "Interview date and time should be after application date and time!\n\n"
                    + "%s's application date and time: %s";
    public static final String MESSAGE_INVALID_STATUS_WITH_INTERVIEW =
            "Interview date and time not required for this status!";
}
