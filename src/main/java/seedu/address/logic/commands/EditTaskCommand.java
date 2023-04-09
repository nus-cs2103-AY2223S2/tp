package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKTYPE;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Date;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Edits the details of an existing task in the task book.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASKTYPE + "TASK_TYPE"
            + " [" + PREFIX_TASK + "TASK_DESCRIPTION] "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "[{DATE}]\n"
            + "For {DATE} field, if task type is deadline, use [" + PREFIX_DATE + "],"
            + "and for event, use [" + PREFIX_START_DATE + "] and [" + PREFIX_END_DATE + "]";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in Pied Piper.";
    public static final String MESSAGE_CONSTRAINTS = "Task type of `T`, `D` or `E` is expected.";
    public static final String MESSAGE_TASK_TYPE_MISMATCH = "The type of task to be edited doesn't "
            + "match the given parameters.";
    public static final String MESSAGE_TODO_INCORRECT_FORMAT = "For task of todo type, no date should be added.";
    public static final String MESSAGE_DEADLINE_INCORRECT_FORMAT =
            "For task of deadline type, [" + PREFIX_DATE + "DATE] is required.";
    public static final String MESSAGE_EVENT_INCORRECT_FORMAT = "For task of event type, ["
            + PREFIX_START_DATE + "START_DATE] and [" + PREFIX_END_DATE + "END_DATE] is required";

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
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) throws CommandException {
        requireNonNull(taskBookModel);
        List<Task> lastShownList = taskBookModel.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && taskBookModel.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        taskBookModel.setTask(taskToEdit, editedTask);
        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit,
            EditTaskDescriptor editTaskDescriptor) throws CommandException {
        assert taskToEdit != null;

        TaskDescription updatedTaskDescription = editTaskDescriptor.getTaskDescription()
                .orElse(taskToEdit.getDescription());
        Comment updatedComment = editTaskDescriptor.getComment().orElse(taskToEdit.getTaskComment());
        String updatedTaskType = editTaskDescriptor.getTaskType().orElse(taskToEdit.getTaskType());
        String updatedDate = "";
        if (updatedTaskType.equals("T")) {
            updatedDate = "";
        } else if (updatedTaskType.equals("D")) {
            updatedDate = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDate());
        } else if (updatedTaskType.equals("E")) {
            Date updatedStartDate = editTaskDescriptor.getStartDate().orElse(null);
            Date updatedEndDate = editTaskDescriptor.getEndDate().orElse(null);
            if (updatedStartDate == null || updatedEndDate == null) {
                throw new CommandException(MESSAGE_TASK_TYPE_MISMATCH + MESSAGE_EVENT_INCORRECT_FORMAT);
            }
            updatedDate = String.format("From: %s To: %s", updatedStartDate, updatedEndDate);
        }

        Task updatedTask = new Task(updatedTaskDescription, updatedDate, updatedTaskType);
        updatedTask.setTaskComment(updatedComment);
        return updatedTask;
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
        private TaskDescription taskDescription;
        private Comment comment;
        private String deadline;
        private Date startDate;
        private Date endDate;
        private String taskType;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskDescription(toCopy.taskDescription);
            setComment(toCopy.comment);
            setDeadline(toCopy.deadline);
            setTaskType(toCopy.taskType);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(comment, deadline, startDate, endDate);
        }

        public void setTaskDescription(TaskDescription taskDescription) {
            this.taskDescription = taskDescription;
        }

        public Optional<TaskDescription> getTaskDescription() {
            return Optional.ofNullable(taskDescription);
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Optional<Comment> getComment() {
            return Optional.ofNullable(comment);
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public Optional<String> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public Optional<String> getTaskType() {
            return Optional.ofNullable(taskType);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskCommand.EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskDescription().equals(e.getTaskDescription())
                    && getComment().equals(e.getComment())
                    && getDeadline().equals(e.getDeadline())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate());
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Optional<Date> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public Optional<Date> getEndDate() {
            return Optional.ofNullable(endDate);
        }
    }
}
