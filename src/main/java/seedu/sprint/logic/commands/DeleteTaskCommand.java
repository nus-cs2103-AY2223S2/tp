package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.logic.commands.EditApplicationCommand.createEditedApplicationWithoutTask;
import static seedu.sprint.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.Model;
import seedu.sprint.model.application.Application;

/**
 * Deletes a task from a specified application in the internship book.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete-task";

    public static final String MESSAGE_USAGE = "Format: delete-task INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "This application does not have an existing task ";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already exists in the internship book";

    private final Index targetIndex;

    /**
     * Creates an DeleteTaskCommand to remove the {@code Task} from a specified application
     */
    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getSortedApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToDeleteTask = lastShownList.get(targetIndex.getZeroBased());
        if (!model.applicationHasTask(applicationToDeleteTask)) {
            throw new CommandException(MESSAGE_TASK_DOES_NOT_EXIST);
        }

        Application editedApplication = createEditedApplicationWithoutTask(applicationToDeleteTask);


        if (model.hasApplication(editedApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }


        model.setApplication(applicationToDeleteTask, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternshipBookChange();
        commandHistory.setLastCommandAsModify();
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, applicationToDeleteTask.getTask()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
