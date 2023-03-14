package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.model.Model;
import trackr.model.TaskList;

/**
 * Clears the task list.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "clear_task";
    public static final String COMMAND_WORD_SHORTCUT = "clear_t";

    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskList(new TaskList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
