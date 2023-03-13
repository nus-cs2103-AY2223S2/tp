package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.commons.util.CollectionUtil;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * Edits the details of an existing task in the task list.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edit_task";
    public static final String COMMAND_WORD_SHORTCUT = "edit_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "TASK NAME] "
            + "[" + PREFIX_DEADLINE + "TASK DEADLINE] "
            + "[" + PREFIX_STATUS + "TASK STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Buy sugar "
            + PREFIX_STATUS + "D";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownTaskList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        TaskName updatedTaskName =
                editTaskDescriptor.getTaskName().orElse(taskToEdit.getTaskName());
        TaskDeadline updatedTaskDeadline =
                editTaskDescriptor.getTaskDeadline().orElse(taskToEdit.getTaskDeadline());
        TaskStatus updatedTaskStatus =
                editTaskDescriptor.getTaskStatus().orElse(taskToEdit.getTaskStatus());

        return new Task(updatedTaskName, updatedTaskDeadline, updatedTaskStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskName taskName;
        private TaskDeadline taskDeadline;
        private TaskStatus taskStatus;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setTaskDeadline(toCopy.taskDeadline);
            setTaskStatus(toCopy.taskStatus);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, taskDeadline, taskStatus);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setTaskDeadline(TaskDeadline taskDeadline) {
            this.taskDeadline = taskDeadline;
        }

        public Optional<TaskDeadline> getTaskDeadline() {
            return Optional.ofNullable(taskDeadline);
        }

        public void setTaskStatus(TaskStatus taskStatus) {
            this.taskStatus = taskStatus;
        }

        public Optional<TaskStatus> getTaskStatus() {
            return Optional.ofNullable(taskStatus);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskName().equals(e.getTaskName())
                    && getTaskDeadline().equals(e.getTaskDeadline())
                    && getTaskStatus().equals(e.getTaskStatus());
        }
    }
}
