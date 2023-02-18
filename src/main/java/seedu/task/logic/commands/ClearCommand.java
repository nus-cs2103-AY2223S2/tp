package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.task.model.Model;
import seedu.task.model.TaskBook;

/**
 * Clears the task book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskBook(new TaskBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
