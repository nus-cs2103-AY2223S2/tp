package vimification.logic.commands;

import vimification.model.Model;
import vimification.model.TaskList;
import vimification.model.person.Person;
import vimification.model.task.Task;
import static java.util.Objects.requireNonNull;

/**
 * Adds a new task to the task list.
 */
public class CreateCommand extends LogicCommand {

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private Task newTask;

    /**
     * Creates a CreateCommand to add the specified {@code Task}
     */
    public CreateCommand(TaskList list, Task task) {
        super(list);
        requireNonNull(task);
        this.newTask = task;
    }

    /**
     * Creates a CreateCommand to add a new task consisting of the specified {@code taskComponents}
     */
    public CreateCommand(TaskList taskList, String... taskComponents) {
        this(taskList, new Task(taskComponents));
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addTask(newTask);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public CommandResult undo() {
        return new CommandResult(String.format("%s", ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && newTask.equals(((CreateCommand) other).newTask)); // state check
    }
}
