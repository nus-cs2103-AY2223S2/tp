package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.library.model.Model;
import seedu.library.model.RatingOrder;

/**
 * Sorts by rating and lists all bookmarks in the library to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS_DESC = "Listed all bookmarks in descending order of rating";

    public static final String MESSAGE_SUCCESS_ASC = "Listed all bookmarks in ascending order of rating";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts bookmarks by ratings in either ascending or descending order.\n"
            + "Parameters: ORDER (either 'asc' or 'desc')\n"
            + "Example: " + COMMAND_WORD + " asc";

    public final String order;

    /**
     * Creates an SortCommand to add the specified {@code String}
     */
    public SortCommand(String order) {
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedBookmarkList(order);
        model.updateSelectedIndex(-1);
        if (order.equals(RatingOrder.DESC)) {
            return new CommandResult(MESSAGE_SUCCESS_DESC);
        } else {
            return new CommandResult(MESSAGE_SUCCESS_ASC);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && order.equals(((SortCommand) other).order)); // state check
    }
}
