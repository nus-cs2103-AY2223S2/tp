package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.AlphabeticalComparator;
import seedu.address.model.application.Application;
import seedu.address.model.application.DeadlineComparator;
import seedu.address.model.application.DefaultComparator;

/**
 * Sorts applications in the order specified by user.
 */
public class SortApplicationCommand extends ApplicationCommand {
    /**
     * Represents permitted values for the inputted order.
     */
    public enum SortingOrder {
        ALPHABETICAL, DEADLINE;
    }

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " Sorts applications in the order you prefer.\n"
            + COMMAND_WORD
            + " alphabetical: Sorts applications in alphabetical order.\n"
            + COMMAND_WORD
            + " deadline: Sorts applications in order of deadline of their upcoming task";

    public static final String MESSAGE_CONSTRAINTS = "You can only sort alphabetically or by deadline.\n"
            + "You also need to specify the order after the command word sort.";

    public static final String MESSAGE_SORT_SUCCESS = "Sorted all applications by order!";

    private final SortingOrder sortingOrder;

    private final Comparator<Application> comparator;

    /**
     * Constructs a {@code SortApplicationCommand} with a comparator that
     * corresponds to the sorting order the user requests.
     *
     * @param userInputtedOrder An order inputted by user.
     */
    public SortApplicationCommand(String userInputtedOrder) {
        requireNonNull(userInputtedOrder);
        checkArgument(isValidSortingOrder(userInputtedOrder), MESSAGE_CONSTRAINTS);
        sortingOrder = SortApplicationCommand.SortingOrder.valueOf(userInputtedOrder.toUpperCase());

        if (sortingOrder == SortingOrder.ALPHABETICAL) {
            comparator = new AlphabeticalComparator();
        } else if (sortingOrder == SortingOrder.DEADLINE) {
            comparator = new DeadlineComparator();
        } else {
            comparator = new DefaultComparator();
        }
    }

    /**
     * Returns true if a given string is a valid sorting order.
     */
    public static boolean isValidSortingOrder(String test) {
        for (SortApplicationCommand.SortingOrder s : SortApplicationCommand.SortingOrder.values()) {
            if (s.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);
        model.updateSortedApplicationList(comparator);
        return new CommandResult(MESSAGE_SORT_SUCCESS);
    }
}
