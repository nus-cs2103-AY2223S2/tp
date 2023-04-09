package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.SortEventKey;
import seedu.address.model.Model;

/**
 * Sorts all events in the address book to the user based on start date time.
 */
public class SortEventCommand extends Command {

    public static final String COMMAND_WORD = "sortevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all events in the address book.\n"
            + "Types of sorting: \n"
            + SortEventKey.SORT_BY_NAME_ASC.getSortEventKeyString() + ": "
            + SortEventKey.SORT_BY_NAME_ASC.getDescription() + "\n"
            + SortEventKey.SORT_BY_NAME_DESC.getSortEventKeyString() + ": "
            + SortEventKey.SORT_BY_NAME_DESC.getDescription() + "\n"
            + SortEventKey.SORT_BY_START_DATE_TIME.getSortEventKeyString()
            + ": " + SortEventKey.SORT_BY_START_DATE_TIME.getDescription() + "\n"
            + SortEventKey.SORT_BY_END_DATE_TIME.getSortEventKeyString()
            + ": " + SortEventKey.SORT_BY_END_DATE_TIME.getDescription() + "\n"
            + "Example: " + COMMAND_WORD + " " + SortEventKey.SORT_BY_START_DATE_TIME.getSortEventKeyString();

    public static final String MESSAGE_SUCCESS = "Sorted all events in the address book successfully";
    public static final String MESSAGE_TOO_FEW_EVENTS = "Too few events in the current list to sort";

    private final SortEventKey sortEventKey;

    public SortEventCommand(SortEventKey sortEventKey) {
        this.sortEventKey = sortEventKey;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getFilteredEventList().size() > 1) {
            model.sortEventList(sortEventKey);
            return new CommandResult(String.format("%s based on the %s", MESSAGE_SUCCESS,
                    sortEventKey.getDescription()));
        } else {
            return new CommandResult(MESSAGE_TOO_FEW_EVENTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortEventCommand // instanceof handles nulls
                && sortEventKey.equals(((SortEventCommand) other).sortEventKey)); // state check
    }
}
