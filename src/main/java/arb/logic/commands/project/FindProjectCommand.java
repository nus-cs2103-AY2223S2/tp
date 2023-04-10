package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;
import arb.model.project.Project;

/**
 * Finds and lists all projects in address book whose title contains any of the argument keywords given
 * and contains any of the tags given and falls within the provided timeframe. Keyword matching is case insensitive.
 */
public class FindProjectCommand extends Command {

    public static final String MESSAGE_END_BEFORE_START_ERROR = "End date cannot be before start date.";

    private static final String MAIN_COMMAND_WORD = "find-project";
    private static final String ALIAS_COMMAND_WORD = "fp";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Finds all projects whose names contain any of "
            + "the specified keywords (case-insensitive), contains any of the tags given (case-insensitive), "
            + "falls within the given timeframe, is linked to the client with the provided client name keywords "
            + "and has the given status and displays them as a list with index numbers.\n"
            + "Parameters: [name/NAME]* [start/START_OF_TIMEFRAME] [end/END_OF_TIMEFRAME] [status/STATUS] "
            + "[tag/TAG]* [client/CLIENT]*\n"
            + "Example: " + MAIN_COMMAND_WORD + " name/sculpture name/digital status/not done client/alice "
            + "tag/personal start/last week end/next year";

    private final Predicate<Project> predicate;

    public FindProjectCommand(Predicate<Project> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredProjectList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredProjectList().size())
                + "\n" + predicate,
                ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProjectCommand // instanceof handles nulls
                && predicate.equals(((FindProjectCommand) other).predicate)); // state check
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }
}
