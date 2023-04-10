package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.modtrek.commons.core.Messages;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.ModuleCodePredicate;

/**
 * Finds and lists all modules in degree progression whose code contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds module with the specified module code "
            + "or by its prefix, credits, semyear, grade, tag(s).\n\n"
            + "Parameters: (<MODULE_CODE>) or "
            + "(" + PREFIX_CODE + " <MODULE_PREFIX>) "
            + "(" + PREFIX_CREDIT + " <MODULE_CREDITS>) "
            + "(" + PREFIX_SEMYEAR + " <SEMESTER_YEAR>) "
            + "(" + PREFIX_GRADE + " <GRADE>) "
            + "(" + PREFIX_TAG + " <TAG>...)\n\n"
            + "Example 1: " + COMMAND_WORD + " CS1101S\n"
            + "Example 2: " + COMMAND_WORD + " /m CS /g A";

    private final ModuleCodePredicate predicate;
    private final List<String> filtersList;

    /**
     * Creates a FindCommand object.
     * @param predicate condition that satisfies the filters of the prefixes
     * @param filtersList list of filters
     */
    public FindCommand(ModuleCodePredicate predicate, List<String> filtersList) {
        requireNonNull(predicate);
        requireNonNull(filtersList);

        this.predicate = predicate;
        this.filtersList = filtersList;
    }

    public List<String> getFiltersList() {
        return filtersList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()),
                false,
                false,
                true,
                false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
