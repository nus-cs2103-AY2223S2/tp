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
 * Marks a project identified using it's displayed index from the address book.
 */
public class MarkProjectCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the project identified by the index number used in the displayed project list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_PROJECT_SUCCESS = "Marked Project: %1$s";

    private final Index targetIndex;

    public MarkProjectCommand(Index targetIndex) {
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

        Project projectToMark = lastShownList.get(targetIndex.getZeroBased());
        projectToMark.markAsDone();

        model.setProject(projectToMark, projectToMark);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        model.updateSortedProjectList(PROJECT_NO_COMPARATOR);
        return new CommandResult(String.format(MESSAGE_MARK_PROJECT_SUCCESS, projectToMark), ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkProjectCommand // instanceof handles nulls
                && targetIndex.equals(((MarkProjectCommand) other).targetIndex)); // state check
    }
}
