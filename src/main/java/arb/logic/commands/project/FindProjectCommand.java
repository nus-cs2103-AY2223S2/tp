package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;
import arb.model.project.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all projects in address book whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindProjectCommand extends Command {

    public static final String COMMAND_WORD = "find-project";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all projects whose titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " digital sculpture";

    private final TitleContainsKeywordsPredicate predicate;

    public FindProjectCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredProjectList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredProjectList().size()),
                ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProjectCommand // instanceof handles nulls
                && predicate.equals(((FindProjectCommand) other).predicate)); // state check
    }
}
