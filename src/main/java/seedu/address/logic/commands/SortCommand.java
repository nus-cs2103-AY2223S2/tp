package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Sorts all the modules either by timeslot or by deadline.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the modules based on their timeslot or "
            + "deadline.\n"
            + "Parameters: sort timeslot | deadline \n"
            + "Example: " + COMMAND_WORD + " timeslot\n"
            + "Example: " + COMMAND_WORD + " deadline";

    private final Comparator<Module> comparator;

    public SortCommand(Comparator<Module> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedModuleList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getDisplayedModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
