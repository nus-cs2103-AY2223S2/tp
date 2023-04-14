package tfifteenfour.clipboard.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Deletes a task from the task page.
 */
public class DeleteTaskCommand extends DeleteCommand {
    public static final String COMMAND_TYPE_WORD = "task";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Deletes a specified task."
            + "Parameters: "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted task in %1$s: %2$s";

    private final Index index;

    /**
     * Creates a {@code DeleteTaskCommand} to delete the task at the specified {@code Index}.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteTaskCommand(Index index) {
        this.index = index;
    }

    /**
     * Deletes the task specified by the index from the selected group in the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of the command execution.
     * @throws CommandException If the selected page is not a task page or if the index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.TASK_PAGE) {
            throw new CommandException("Wrong page. Navigate to task page to delete task");
        }

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Task> lastShownList = selectedGroup.getUnmodifiableFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(index.getZeroBased());
        selectedGroup.deleteTask(taskToDelete);

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, selectedGroup, taskToDelete), willModifyState);
    }
}
