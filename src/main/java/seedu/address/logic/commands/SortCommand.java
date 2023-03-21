package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.comparator.ListingComparator;

/**
 * Deletes a listing identified using it's displayed index from the listing book.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the listing displayed according to the specified field.\n"
            + "Parameters: " + PREFIX_FIELD + "FIELD_COMPARED(none, title, description, applicants)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FIELD + "number of applicants";

    public static final String MESSAGE_SUCCESS = "Listings have been sorted.";

    private final ListingComparator comparator;

    /**
     * Creates a SortCommand to sort the listings displayed.
     * @param comparator logic used to sort the listing
     */
    public SortCommand(ListingComparator comparator) {
        requireNonNull(comparator);

        this.comparator = comparator;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateSortedListingBook(comparator);

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
