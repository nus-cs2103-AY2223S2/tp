package bookopedia.logic.commands;

import static java.util.Objects.requireNonNull;

import bookopedia.model.Model;

/**
 * Sorts all deliveries in Bookopedia according to delivery status.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all deliveries according to their delivery status";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
