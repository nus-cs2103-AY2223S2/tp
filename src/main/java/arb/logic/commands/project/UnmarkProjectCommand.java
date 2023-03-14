package arb.logic.commands.project;

import static arb.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static arb.model.Model.PROJECT_NO_COMPARATOR;
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
 * Unmarks a project identified using its displayed index from the address book.
 */
public class UnmarkProjectCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the project identified by the index number used in the displayed project list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_PROJECT_SUCCESS = "Unmarked Project: %1$s";

    private final Index targetIndex;

    public UnmarkProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getSortedProjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (currentListBeingShown != ListType.PROJECT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_PROJECT);
        }

        Project projectToUnmark = lastShownList.get(targetIndex.getZeroBased());
        projectToUnmark.unmarkAsDone();

        model.setProject(projectToUnmark, projectToUnmark);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        model.updateSortedProjectList(PROJECT_NO_COMPARATOR);
        return new CommandResult(String.format(MESSAGE_UNMARK_PROJECT_SUCCESS, projectToUnmark), ListType.PROJECT);
    }
}
