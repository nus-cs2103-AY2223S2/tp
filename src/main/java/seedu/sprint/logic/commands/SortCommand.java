package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.logic.parser.SortCommandParser.SortingOrder;
import seedu.sprint.logic.parser.SortCommandParser.SortingSequence;
import seedu.sprint.model.Model;
import seedu.sprint.model.application.AlphabeticalComparator;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.ApplicationHasTaskPredicate;
import seedu.sprint.model.application.DeadlineComparator;
import seedu.sprint.model.application.DefaultComparator;

/**
 * Sorts applications in the order specified by user.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "Format: "
            + COMMAND_WORD
            + " <a/d> <alphabetical/deadline>\n"
            + "Example: "
            + COMMAND_WORD
            + " d alphabetical";

    public static final String MESSAGE_CONSTRAINTS = "You can only sort alphabetically or by deadline.\n"
            + "You also need to specify whether to sort in ascending or descending order.";

    public static final String MESSAGE_SORT_ALPHABETICAL_SUCCESS = "Sorted all applications by alphabetical order!";

    public static final String MESSAGE_SORT_DEADLINE_SUCCESS = "Listed all applications with task deadlines"
            + " and sorted them in order!";

    private final SortingOrder sortingOrder;

    private final SortingSequence sortingSequence;

    private final Comparator<Application> comparator;

    /**
     * Constructs a {@code SortCommand} with a comparator that
     * corresponds to the sorting order the user requests.
     *
     * @param sortingOrder The sorting order requested by user.
     * @param sortingSequence The sorting sequence requested by user.
     */
    public SortCommand(SortingOrder sortingOrder, SortingSequence sortingSequence) {
        this.sortingOrder = sortingOrder;
        this.sortingSequence = sortingSequence;

        if (sortingOrder == SortingOrder.ALPHABETICAL) {
            comparator = new AlphabeticalComparator(sortingSequence);
        } else if (sortingOrder == SortingOrder.DEADLINE) {
            comparator = new DeadlineComparator(sortingSequence);
        } else {
            comparator = new DefaultComparator();
        }
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        if (this.sortingOrder == SortingOrder.DEADLINE) {
            model.updateFilteredApplicationList(new ApplicationHasTaskPredicate());
        }
        model.updateSortedApplicationList(comparator);
        if (this.sortingOrder == SortingOrder.DEADLINE) {
            return new CommandResult(MESSAGE_SORT_DEADLINE_SUCCESS);
        }
        return new CommandResult(MESSAGE_SORT_ALPHABETICAL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortingOrder.equals(((SortCommand) other).sortingOrder)
                && sortingSequence.equals(((SortCommand) other).sortingSequence)
                && comparator.equals(((SortCommand) other).comparator));
    }
}
