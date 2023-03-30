package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

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

import java.util.List;
import java.util.Optional;

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

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

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
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownTasks.get(index.getZeroBased());
        try {
            editTaskDescriptor.UpdateTankInstance(model);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_BAD_TANK_INDEX);
        }
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.equals(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

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
        Priority updatedPriority = editTaskDescriptor.getPriority().orElse(taskToEdit.getPriority());

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

        public void UpdateTankInstance(Model model) {
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
