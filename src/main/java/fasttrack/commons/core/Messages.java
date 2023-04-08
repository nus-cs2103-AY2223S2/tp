package fasttrack.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_EXPENSES_LISTED_OVERVIEW = "%1$d expenses listed";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The index provided is invalid";
    public static final String MESSAGE_INVALID_CATEGORY_DISPLAYED_INDEX = "The category index provided is invalid!";
    public static final String MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX = "The expense index provided is invalid!";
    public static final String MESSAGE_INVALID_RECURRING_EXPENSE_DISPLAYED_INDEX = "The recurring expense index "
            + "provided is invalid!";
    public static final String MESSAGE_INVALID_EXPENSE_CATEGORY = "The provided category does not exist!";

    public static final String MESSAGE_INVALID_EDIT_FOR_EXPENSE = "Please specify an edit to at least "
            + "the date, name, price or category of the expense!";
    public static final String MESSAGE_INVALID_EDIT_FOR_CATEGORIES = "Please specify an edit to at least "
            + "the category name or the category's summary.";
    public static final String MESSAGE_SUCCESSFULLY_EDITED_CATEGORY = "Edited category: %1$s";
    public static final String MESSAGE_SUCCESSFULLY_EDITED_EXPENSE = "Edited expense: %1$s";
    public static final String MESSAGE_SUCCESSFULLY_EDITED_RECURRING = "Edited recurring expense generator: %1$s";
    public static final String MESSAGE_INVALID_ENUM_FOR_FREQUENCY = "The frequency provided is invalid!"
            + "Please choose from the following: daily, weekly, monthly or yearly.";
    public static final String MESSAGE_INVALID_CATEGORY_NAME = "Please provide a category name!";
    public static final String MESSAGE_INVALID_EXPENSE_NAME = "Please provide an expense name!";
    public static final String MESSAGE_ALREADY_EXISTING_CATEGORY = "This category name is already used!";


}
