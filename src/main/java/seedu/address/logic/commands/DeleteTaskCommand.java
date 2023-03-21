package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditApplicationCommand.createEditedApplicationWithoutTask;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;

/**
 * Deletes a task from an application in the internship book.
 */
public class DeleteTaskCommand extends ApplicationCommand {
    public static final String COMMAND_WORD = "delete-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task associated with the application identified by the index number "
            + "used in the displayed application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "This application does not have an existing task ";
    private final Index targetIndex;

    /**
     * Creates an DeleteTaskCommand to remove the {@code Task} from a specified application
     */
    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToDeleteTask = lastShownList.get(targetIndex.getZeroBased());
        if (!model.applicationHasTask(applicationToDeleteTask)) {
            throw new CommandException(MESSAGE_TASK_DOES_NOT_EXIST);
        }

        Application editedApplication = createEditedApplicationWithoutTask(applicationToDeleteTask);
        model.setApplication(applicationToDeleteTask, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, applicationToDeleteTask.getTask()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
