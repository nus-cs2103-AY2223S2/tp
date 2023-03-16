package teambuilder.logic.commands;

import teambuilder.commons.core.Messages;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.person.Person;

import teambuilder.logic.commands.enums.SortBy;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_SUCCESS = "Sorted all persons.";
    private final String order;
    private final String sortByString;
    private SortBy sortBy;

    public SortCommand(String order, String sortByString) {
        requireNonNull(order);
        requireNonNull(sortByString);

        this.order = order;
        this.sortByString = sortByString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!checkOrder()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SORTING_ORDER);
        }

        if (!processSortBy()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SORT_BY);
        }

        switch (sortBy) {
        case TAG_COUNT:
            Comparator<Person> comparator = Comparator.comparingInt(Person::getNumTags);

            if (order.equals("desc")) {
                comparator = comparator.reversed();
            }

            model.updateSort(comparator);
            break;
        default:
            break;
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /* Helper function to process sort by */
    private boolean processSortBy() {
        if (sortByString.equals("tCount")) {
            sortBy = SortBy.TAG_COUNT;
            return true;
        }
        return false;
    }

    private boolean checkOrder() {
        return order.equals("desc") || order.equals("asc");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand e = (SortCommand) other;
        return order.equals(e.order)
                && sortByString.equals(e.sortByString);
    }
}
