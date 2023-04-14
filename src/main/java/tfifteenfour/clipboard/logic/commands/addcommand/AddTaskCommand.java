package tfifteenfour.clipboard.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Adds a new task to the selected group.
 */
public class AddTaskCommand extends AddCommand {
    public static final String COMMAND_TYPE_WORD = "task";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Adds a task to the selected group. "
            + "Parameters: "
            + "TASK_NAME\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + "CA1 Oral Presentation\n";

    public static final String MESSAGE_SUCCESS = "New task added in %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the course";
    public static final String MESSAGE_WRONG_PAGE = "Wrong page. Navigate to task page to add tasks";

    private final Task taskToAdd;

    /**
     * Creates an AddTaskCommand object to add a task.
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the AddTaskCommand to add a task to the selected group.
     *
     * @param model The model of the application.
     * @return The result of the command execution.
     * @throws CommandException If the task already exists in the group or if the user is not on the task page.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.TASK_PAGE) {
            throw new CommandException(AddTaskCommand.MESSAGE_WRONG_PAGE);
        }

        Group targetGroup = currentSelection.getSelectedGroup();
        if (targetGroup.hasTask(taskToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        targetGroup.addTask(taskToAdd);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, targetGroup, taskToAdd), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && taskToAdd.equals(((AddTaskCommand) other).taskToAdd));
    }
}
