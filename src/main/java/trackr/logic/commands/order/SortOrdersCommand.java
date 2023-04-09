package trackr.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_CRITERIA;

import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.model.Model;
import trackr.model.order.SortOrdersComparator;

/**
 * Sorts all orders in the order list using a criteria given.
 */
public class SortOrdersCommand extends Command {
    public static final String COMMAND_WORD = "sort_order";
    public static final String COMMAND_WORD_SHORTCUT = "sort_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all orders "
            + "based on given criteria.\n"
            + "Parameters: " + "[" + PREFIX_CRITERIA + "CRITERIA] "
            + "Example: " + COMMAND_WORD + PREFIX_CRITERIA + "Status";

    public static final String MESSAGE_SUCCESS = "Orders sorted!";

    private SortOrdersComparator sortOrdersComparator;

    /**
     * Creates a SortOrdersCommand to sort the order list.
     *
     * @param sortOrdersComparator The comparator used to compare 2 orders according to a given criteria.
     */
    public SortOrdersCommand(SortOrdersComparator sortOrdersComparator) {
        requireNonNull(sortOrdersComparator);
        this.sortOrdersComparator = sortOrdersComparator;
    }

    /**
     * Sorts the filtered order list using the sortOrdersComparator.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Success message of the sort operation for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredOrderList(sortOrdersComparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortOrdersCommand // instanceof handles nulls
                && sortOrdersComparator.equals(((SortOrdersCommand) other).sortOrdersComparator)); // state check
    }

}
