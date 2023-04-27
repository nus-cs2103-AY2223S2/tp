package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.logic.commands.EditApplicationCommand.createEditedApplication;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.sprint.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.Model;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.task.Deadline;

/**
 * Adds a task to an application in the internship book.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "add-task";

    public static final String MESSAGE_USAGE = "Format: INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE + "DEADLINE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Online Assessment "
            + PREFIX_DEADLINE + "01-04-2023 ";


    public static final String MESSAGE_SUCCESS = "New task added: %1$s.";
    public static final String MESSAGE_TASK_EXISTS = "This application already has an existing task.";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already exists in the internship book";

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
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
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


        if (model.hasApplication(editedApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }


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
