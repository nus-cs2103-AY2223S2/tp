package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditApplicationCommand.createEditedApplication;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;
import seedu.address.model.task.Deadline;

/**
 * Adds a task to an application in the internship book.
 */
public class AddTaskCommand extends ApplicationCommand {
    public static final String COMMAND_WORD = "add-task";

    public static final String MESSAGE_USAGE = "Format: INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE + "DEADLINE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Online Assessment "
            + PREFIX_DEADLINE + "01-04-2023 ";


    public static final String MESSAGE_SUCCESS = "New task added: %1$s.";
    public static final String MESSAGE_TASK_EXISTS = "This application already has an existing task.";

    private final Index targetIndex;
    private final EditApplicationDescriptor editApplicationDescriptor;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to an application.
     */
    public AddTaskCommand(Index targetIndex, EditApplicationDescriptor editApplicationDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editApplicationDescriptor);

        this.targetIndex = targetIndex;
        this.editApplicationDescriptor = new EditApplicationDescriptor(editApplicationDescriptor);
    }

    @Override
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getSortedApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToAddTask = lastShownList.get(targetIndex.getZeroBased());
        if (model.applicationHasTask(applicationToAddTask)) {
            throw new CommandException(MESSAGE_TASK_EXISTS);
        }

        Application editedApplication = createEditedApplication(applicationToAddTask, editApplicationDescriptor);

        model.setApplication(applicationToAddTask, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternshipBookChange();
        commandHistory.setLastCommandAsModify();
        String displayMessage = MESSAGE_SUCCESS;
        if (editedApplication.hasOutdatedTask()) {
            displayMessage = Deadline.DEADLINE_HAS_PASSED + displayMessage;
        }
        return new CommandResult(String.format(displayMessage, editedApplication.getTask()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        // state check
        AddTaskCommand a = (AddTaskCommand) other;
        return targetIndex.equals(a.targetIndex)
                && editApplicationDescriptor.equals(a.editApplicationDescriptor);
    }
}
