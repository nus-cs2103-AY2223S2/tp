package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditApplicationCommand.createEditedApplication;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;

/**
 * Edits an existing task in the internship book.
 */
public class EditTaskCommand extends ApplicationCommand {
    public static final String COMMAND_WORD = "edit-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing task "
            + "in the internship book. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Online Assessment "
            + PREFIX_DEADLINE + "01-04-2023 ";


    public static final String MESSAGE_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "This application does not have an existing task ";
    private final Index targetIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an EditTaskCommand to edit the specified {@code Task} in an application
     */
    public EditTaskCommand(Index targetIndex, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editTaskDescriptor);

        this.targetIndex = targetIndex;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getSortedApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEditTask = lastShownList.get(targetIndex.getZeroBased());
        if (!model.applicationHasTask(applicationToEditTask)) {
            throw new CommandException(MESSAGE_TASK_DOES_NOT_EXIST);
        }

        Application editedApplication = createEditedApplication(applicationToEditTask, editTaskDescriptor);
        model.setApplication(applicationToEditTask, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedApplication.getTask()));
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
        return targetIndex.equals(e.targetIndex)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Description description;
        private Deadline deadline;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setDescription(toCopy.description);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, deadline);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
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
                    && getDeadline().equals(e.getDeadline());
        }
    }
}
