package tfifteenfour.clipboard.logic.commands.editcommand;

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
 * Edits the name of a task in the group.
 */
public class EditTaskCommand extends EditCommand {
    public static final String COMMAND_TYPE_WORD = "task";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Edits a task name."
            + "Parameters: INDEX (must be a positive integer) + NEW_TASK_NAME\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " 1 CA2 Pitch and Demo";

    public static final String MESSAGE_SUCCESS = "Edited task: %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the group";

    private final Index index;
    private final Task newTask;

    /**
     * Constructs an {@code EditTaskCommand} with the specified index and new task.
     * @param index The index of the task to be edited.
     * @param newTask The new task to be set.
     */
    public EditTaskCommand(Index index, Task newTask) {
        this.index = index;
        this.newTask = newTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.TASK_PAGE) {
            throw new CommandException("Wrong page. Navigate to task page to edit task");
        }

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Task> lastShownList = selectedGroup.getUnmodifiableTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        } else if (selectedGroup.hasTask(newTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        newTask.setGrades(taskToEdit.getGrades());

        selectedGroup.setTask(taskToEdit, newTask);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, taskToEdit, newTask), willModifyState);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EditTaskCommand)) {
            return false;
        }
        EditTaskCommand other = (EditTaskCommand) obj;
        return index.equals(other.index) && newTask.equals(other.newTask);
    }
}
