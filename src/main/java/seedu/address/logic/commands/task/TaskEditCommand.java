package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tank.Tank;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskHasNoPriorityException;

/**
 * Edits the details of an existing task in the task list.
 */
public class TaskEditCommand extends TaskCommand {

    public static final String TASK_COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TASK_COMMAND_WORD
            + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TANK + "TANK] "
            + "[" + PREFIX_PRIORITY + "PRIORITY] \n"
            + "Example: " + COMMAND_WORD + " " + TASK_COMMAND_WORD + " 1 "
            + PREFIX_TANK + "1 "
            + PREFIX_PRIORITY + "high "
            + PREFIX_DESCRIPTION + "new task ";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";
    public static final String MESSAGE_BAD_TANK_INDEX = "The given tank index is invalid.";
    public static final String MESSAGE_CANNOT_EDIT_REMINDER = "You cannot edit feeding reminders!"
            + " Leave it to Fish Ahoy!";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Constructor for task edit command
     * @param index index of task of interest
     * @param editTaskDescriptor the new task after being edited
     */
    public TaskEditCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTasks = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownTasks.size()) {
            throw new CommandException(Messages.MESSAGE_TASK_INDEX_OUTOFBOUNDS);
        }

        Task taskToEdit = lastShownTasks.get(index.getZeroBased());
        if (taskToEdit.getIsReminder()) {
            throw new CommandException(MESSAGE_CANNOT_EDIT_REMINDER);
        }

        try {
            editTaskDescriptor.updateTankInstance(model);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_BAD_TANK_INDEX);
        }
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.equals(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.setTask(taskToEdit, editedTask);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates the edited task
     * @param taskToEdit original task
     * @param editTaskDescriptor task descriptor of the new task
     * @return new Task instance
     */
    public static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Tank updatedTank = null;
        if (editTaskDescriptor.tank != null) {
            updatedTank = editTaskDescriptor.tank;
        } else {
            if (taskToEdit.isTankRelatedTask()) {
                updatedTank = taskToEdit.getTank();
            }
        }
        Priority updatedPriority;
        try {
            updatedPriority = editTaskDescriptor.getPriority().orElse(taskToEdit.getPriority());
        } catch (TaskHasNoPriorityException e) { //orElse is always executed so we need this catch block
            //if editTask has p
            if (editTaskDescriptor.getPriority().isPresent()) {
                updatedPriority = editTaskDescriptor.getPriority().get();
            } else { //since we are in this catch block, the original task has not priority. This else block is
                //if the edited task is taking the original priority
                updatedPriority = null;
            }
        }
        return new Task(updatedDescription, updatedTank, updatedPriority);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Description description;
        private Index tankIndex = null;
        private Priority priority;
        private Tank tank;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setDescription(toCopy.description);
            setPriority(toCopy.priority);
            setTankIndex(toCopy.tankIndex);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, tankIndex, priority);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setTankIndex(Index tankIndex) {
            this.tankIndex = tankIndex;
        }

        public Optional<Index> getTankIndex() {
            return Optional.ofNullable(tankIndex);
        }

        public Optional<Tank> getTank() {
            return Optional.ofNullable(tank);
        }

        /**
         * Updates the tank of this editTaskDescriptor to the real instance
         * @param model model of Fish Ahoy!
         */
        public void updateTankInstance(Model model) {
            if (tankIndex == null) {
                return;
            }
            tank = model.getFilteredTankList().get(tankIndex.getZeroBased());
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

            return getDescription().equals(e.getDescription())
                    && getTankIndex().equals(e.getTankIndex())
                    && getPriority().equals(e.getPriority());
        }
    }

}
