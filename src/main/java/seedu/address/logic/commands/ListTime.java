package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SCHEDULED;

import seedu.address.model.Model;

/**
 * Lists patients with scheduled time by order.
 */
public class ListTime extends Command {

    public static final String COMMAND_WORD = "listTime";

    public static final String MESSAGE_SUCCESS = "Listed patients by scheduled time";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateScheduledList(PREDICATE_SCHEDULED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
