package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.project.Project;

/**
 * Deletes a project identified using its displayed index from the address book.
 */
public class DeleteProjectCommand extends Command {

    public static final String COMMAND_WORD = "delete-project";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the project identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX(must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteProjectCommand to delete the specified {@code Project}
     */
    public DeleteProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProject(projectToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete), ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short-circuit if same object
                || (other instanceof DeleteProjectCommand // handles null
                && targetIndex.equals(((DeleteProjectCommand) other).targetIndex)); // state check
    }
}
