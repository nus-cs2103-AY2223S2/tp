package arb.logic.commands.project;

import static arb.logic.parser.CliSyntax.PREFIX_OPTION;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arb.commons.core.sorting.ProjectSortingOption;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Sorts all projects in the address book.
 */
public class SortProjectCommand extends Command {

    private static final String MAIN_COMMAND_WORD = "sort-project";
    private static final String ALIAS_COMMAND_WORD = "sp";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Sorts the project list. "
            + "Parameters: "
            + PREFIX_OPTION + "OPTION\n"
            + "The options are \'name\' and \'deadline\' and \'price\'\n"
            + "Example: " + MAIN_COMMAND_WORD + " "
            + PREFIX_OPTION + "name";

    private final ProjectSortingOption sorter;

    /**
     * @param sorter to sort the project list with
     */
    public SortProjectCommand(ProjectSortingOption sorter) {
        this.sorter = sorter;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateSortedProjectList(sorter.getComparator());
        return new CommandResult(sorter.getSuccessMessage(), ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortProjectCommand // instanceof handles nulls
                && sorter.equals(((SortProjectCommand) other).sorter)); // state check
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }
}
