package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNKNOWN_TIMETABLE_COMMAND = "Unknown command "
            + "\nOnly timetable_date command is allowed in timetable:"
            + " timetable_date date/YYYY-mm-DD"
            + "\nFor example: timetable_date date/2023-03-01";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_EITHER_INDEX_OR_ID = "Select either by list or job id only.";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX = "The reminder index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_JOB_ID = "The job id provided is invalid";
    public static final String MESSAGE_INVALID_JOB_DISPLAYED_INDEX = "The job index provided is invalid";
    public static final String MESSAGE_DELIVERY_JOB_LISTED_OVERVIEW = "%1$d jobs listed!";
    public static final String COMMAND_NOT_ALLOW = "Command not allowed in this window!";

}
