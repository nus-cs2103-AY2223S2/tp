package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_GROUPS_LISTED_OVERVIEW = "%1$d groups listed!";
    public static final String MESSAGE_INVALID_DATA = "Invalid data found!";
    public static final String MESSAGE_NO_IMPORT = "No import data found!";
    public static final String MESSAGE_IMPORT_SIZE_WRONG = "Invalid size of import!";

    public static final String MESSAGE_UNKNOWN_ISOLATED_EVENT = "No such event in isolated event list";
    public static final String MESSAGE_INVALID_ISOLATED_EVENT_LIST = "No such index in the isolated event list";
    public static final String MESSAGE_INVALID_RECURRING_EVENT_LIST = "No such index in the recurring event list";
    public static final String MESSAGE_UNKNOWN_RECURRING_EVENT = "No such event in recurring event list";
    public static final String MESSAGE_EVENT_CLASH = "You already have an event at this time period:\n%1$s";
    public static final String MESSAGE_EVENT_INVALID_DATE = "Invalid date! The date of event cannot be before the"
                                                                + " current time!";
    public static final String MESSAGE_NONEXISTENT_DATE = "Date do not exist, please key in a valid date!";

    public static final String MESSAGE_INVALID_EVENT_INDEX = "The event index provided is invalid";

    public static final String MESSAGE_EVENT_START_AFTER_END = "The end time should be later than the start time";

    public static final String MESSAGE_INVALID_GROUP_DISPLAYED_INDEX = "The group index provided is invalid";
    public static final String MESSAGE_INVALID_GROUP = "The group(s) provided does not exist";
}
