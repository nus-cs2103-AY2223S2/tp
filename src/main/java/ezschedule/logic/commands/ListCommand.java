package ezschedule.logic.commands;

import static java.util.Objects.requireNonNull;

import ezschedule.model.Model;

/**
 * Lists all {@code Event} in the {@code Scheduler} to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all events";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        model.updateFindEventList(Model.PREDICATE_SHOW_NO_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }
}
