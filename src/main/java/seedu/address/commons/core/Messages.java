package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_MULTIPLE_INDEX =
            "At least one of the indexes provided is invalid";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_VIEW_PERSON_CONTACT_DETAILS = "%s's contact details displayed";
    public static final String MESSAGE_INVALID_INDEX =
            "The person index provided is invalid. Please enter \"list\" again to retrieve the list and try again.";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_PERSON_NOT_FOUND = "There is no such person in your contact list.";
    public static final String MESSAGE_MULTIPLE_PERSONS_FOUND = "There are multiple people who have the same name. "
        + "You have to be more specific";
    public static final String MESSAGE_IOEXCEPTION = "Unable to create \"ModCheck\" file to write";
    public static final String MESSAGE_EXPORT_PERSON_CONTACT_DETAILS = "%s's contact details exported";
}

