package ezschedule.logic.commands;

import static java.util.Objects.requireNonNull;

import ezschedule.model.Model;
import ezschedule.model.Scheduler;

/**
 * Clears the {@code Scheduler}.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Scheduler has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setScheduler(new Scheduler());
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }
}
