package seedu.powercards.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CARD_DISPLAYED_INDEX = "The card index provided is invalid";
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "The deck index provided is invalid";
    public static final String MESSAGE_CARDS_LISTED_OVERVIEW = "%1$d card(s) listed!";
    public static final String MESSAGE_DECKS_LISTED_OVERVIEW = "%1$d deck(s) listed!";
    public static final String MESSAGE_NO_DECK_SELECTED = "No deck currently selected "
            + "- Select a deck to run '%1$s' command :)";
    public static final String MESSAGE_DECK_SELECTED = "You've currently selected a deck "
            + "- Unselect the deck to run '%1$s' command :)";
    public static final String MESSAGE_NOT_IN_REVIEW = "You're currently not in a review "
            + "- Start a review to run '%1$s' command :)";
    public static final String MESSAGE_IN_REVIEW = "You're currently in a review "
            + "- End the review to run '%1$s' command :)";
}
