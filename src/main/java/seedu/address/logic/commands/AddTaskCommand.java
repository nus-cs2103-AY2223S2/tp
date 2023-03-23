package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditApplicationCommand.createEditedApplication;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;

/**
 * Adds a task to an application in the internship book.
 */
public class AddTaskCommand extends ApplicationCommand {
    public static final String COMMAND_WORD = "add-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to an existing application "
            + "in the internship book. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE + "DEADLINE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Online Assessment "
            + PREFIX_DEADLINE + "01-04-2023 ";


    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_TASK_EXISTS = "This application already has an existing task ";

    private final Index targetIndex;
    private final EditApplicationDescriptor editApplicationDescriptor;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to an application
     */
    public AddTaskCommand(Index targetIndex, EditApplicationDescriptor editApplicationDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editApplicationDescriptor);

        this.targetIndex = targetIndex;
        this.editApplicationDescriptor = new EditApplicationDescriptor(editApplicationDescriptor);
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedApplication.getTask()));
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
