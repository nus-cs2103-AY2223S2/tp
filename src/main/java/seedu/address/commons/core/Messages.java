package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format. \n%1$s";
    public static final String MESSAGE_INVALID_ELDERLY_DISPLAYED_INDEX = "The elderly index provided is invalid.";
    public static final String MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX = "The volunteer index provided is invalid.";
    public static final String MESSAGE_INVALID_TO_EDIT_NRIC = "The NRIC of the to be edited person is invalid.\n%s";
    public static final String MESSAGE_INVALID_PERSON_NRIC = "The %1$s NRIC provided is invalid.\n%2$s";
    public static final String MESSAGE_BOTH_INVALID_NRIC = "Both NRICs provided are invalid.\n%s";
    public static final String MESSAGE_LISTED_OVERVIEW = "Listed %1$d volunteer, %2$d elderly and %3$d pair.";
    public static final String MESSAGE_NRIC_NOT_EXIST = "There is no such person with the given NRIC.";
    public static final String MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS =
            "The NRIC provided belongs to an existing volunteer in the database.";
    public static final String MESSAGE_DUPLICATE_PERSON_IN_ELDERLY =
            "The NRIC provided belongs to an existing elderly in the database.";
    public static final String MESSAGE_NO_FIELD_PROVIDED = "At least one field must be provided.";
    public static final String MESSAGE_ELDERLY_NOT_FOUND =
            "The elderly with NRIC %1$s does not exist in FriendlyLink";
    public static final String MESSAGE_VOLUNTEER_NOT_FOUND =
            "The volunteer with NRIC %1$s does not exist in FriendlyLink";
    public static final String MESSAGE_PAIR_NOT_FOUND =
            "The pair consisting of elderly with NRIC %1$s and volunteer with NRIC %2$s"
                    + " does not exist in FriendlyLink";
    public static final String MESSAGE_DUPLICATE_PAIR =
            "This pair consisting of elderly with NRIC %1$s"
                    + " and volunteer with NRIC %2$s already exists in FriendlyLink";
    public static final String MESSAGE_WARNING_REGION = "\nWarning: The paired volunteer's and elderly's region "
            + "do not match";
    public static final String MESSAGE_WARNING_AVAILABLE_DATES = "\nWarning: The paired elderly and volunteer "
            + "do not have common available dates";
    public static final String MESSAGE_FIELD_CANNOT_BE_EMPTY = "Invalid arguments. \n"
            + "%s can take any values, but it should not be empty";

}
