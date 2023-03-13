package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static arb.logic.parser.CliSyntax.PREFIX_OPTION;

import arb.commons.core.sorting.SortingOption;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Sorts all projects in the address book.
 */
public class SortProjectCommand extends Command {

    public static final String COMMAND_WORD = "sort-project";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the project list. "
            + "Parameters: "
            + PREFIX_OPTION + "OPTION\n"
            + "The options are \'name\' and \'deadline\'\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OPTION + "name";

    private final SortingOption sortingOption;

    /**
     * @param sortingOption to sort the project list with
     */
    public SortProjectCommand(SortingOption sortingOption) {
        this.sortingOption = sortingOption;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateSortedProjectList(sortingOption.getComparator());
        return new CommandResult(sortingOption.getSuccessMessage(), ListType.PROJECT);
    }
}
