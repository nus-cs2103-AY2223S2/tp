package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SortApplicationCommandParser.SortingOrder;
import seedu.address.logic.parser.SortApplicationCommandParser.SortingSequence;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.AlphabeticalComparator;
import seedu.address.model.application.Application;
import seedu.address.model.application.ApplicationHasTaskPredicate;
import seedu.address.model.application.DeadlineComparator;
import seedu.address.model.application.DefaultComparator;

/**
 * Sorts applications in the order specified by user.
 */
public class SortApplicationCommand extends ApplicationCommand {
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
     * Constructs a {@code SortApplicationCommand} with a comparator that
     * corresponds to the sorting order the user requests.
     *
     * @param sortingOrder The sorting order requested by user.
     * @param sortingSequence The sorting sequence requested by user.
     */
    public SortApplicationCommand(SortingOrder sortingOrder, SortingSequence sortingSequence) {
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
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        if (this.sortingOrder == SortingOrder.DEADLINE) {
            model.updateFilteredApplicationList(new ApplicationHasTaskPredicate());
        }
        model.updateSortedApplicationList(comparator);
        model.commitInternshipBookChange();
        commandHistory.setLastCommandAsModify();
        if (this.sortingOrder == SortingOrder.DEADLINE) {
            return new CommandResult(MESSAGE_SORT_DEADLINE_SUCCESS);
        }
        return new CommandResult(MESSAGE_SORT_ALPHABETICAL_SUCCESS);
    }
}
