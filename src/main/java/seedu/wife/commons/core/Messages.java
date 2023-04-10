package seedu.wife.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%s";
    public static final String MESSAGE_INVALID_FOOD_DISPLAYED_INDEX = "The food item index provided is invalid";
    public static final String MESSAGE_FOODS_LISTED_OVERVIEW = "%1$d food item(s) found!";
    public static final String MESSAGE_TAG_NOT_FOUND = "There is no %1$s Tag!";
    public static final String MESSAGE_SUCCESSFUL_FOOD_TAG = "%s successfully tagged with %s";
    public static final String MESSAGE_SUCCESSFUL_FOOD_UNTAG = "%s successfully untagged from %s";
    public static final String MESSAGE_DOUBLE_TAG = "You have already tagged %s with %s.";
    public static final String MESSAGE_INVALID_INDEX = "Index you entered is invalid.";
    public static final String MESSAGE_TAG_CREATE_SUCCESS = "Tag(s) successfully created:";
    public static final String MESSAGE_DUPLICATE_TAG = "The tag(s) you are trying to create has been created before.";
    public static final String MESSAGE_EMPTY_ARGUMENT = "You missed out the argument for the command, please refer"
            + " to help for more info. \n %s";
    public static final String MESSAGE_MAXIMUM_TAG_FOOD = "You have reached the maximum tag limit for %s.";
}
