package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static final String MESSAGE_UNMARK_PROJECT_SUCCESS = "Unmarked Project: %1$s";

    private static final String MAIN_COMMAND_WORD = "unmark";
    private static final String ALIAS_COMMAND_WORD = "up";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD
            + ": Unmarks the project identified by the index number used in the displayed project list.\n"
            + "Parameters: <index> (must be a positive integer)\n"
            + "Example: " + MAIN_COMMAND_WORD + " 1";

    private final Index targetIndex;

    public UnmarkProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getSortedProjectList();

        if (currentListBeingShown != ListType.PROJECT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_PROJECT);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToUnmark = lastShownList.get(targetIndex.getZeroBased());
        model.markProjectAsNotDone(projectToUnmark);
        model.resetFilteredAndSortedProjectList();
        return new CommandResult(String.format(MESSAGE_UNMARK_PROJECT_SUCCESS, projectToUnmark), ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkProjectCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkProjectCommand) other).targetIndex)); // state check
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }
}
