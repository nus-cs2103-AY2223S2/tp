package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.comparator.ListingComparator;

/**
 * Sorts the displayed listings according to the provided comparator.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the listing displayed according to the specified field.\n"
            + "Parameters: " + PREFIX_FIELD + "FIELD_COMPARED(none, title, description, applicants)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FIELD + "applicants";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SortCommand)) {
            return false;
        }

        SortCommand that = (SortCommand) o;

        return comparator == that.comparator;
    }

}
