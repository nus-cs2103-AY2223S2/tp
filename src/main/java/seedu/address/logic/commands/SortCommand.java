package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import java.util.List;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.comparator.ListingComparator;
import seedu.address.model.listing.Listing;

/**
 * Deletes a listing identified using it's displayed index from the listing book.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the listing displayed according to the specified field.\n"
            + "Parameters: " + PREFIX_FIELD + "FIELD_COMPARED(title, description, number of applicants)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FIELD + "number of applicants";

    public static final String MESSAGE_SUCCESS = "Listing: %1$s has been deleted!";

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
        List<Listing> lastShownList = model.getDisplayedListingBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
