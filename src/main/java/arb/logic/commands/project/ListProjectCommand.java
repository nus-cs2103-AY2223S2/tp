package arb.logic.commands.project;

import static arb.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Lists all projects in the address book to the user.
 */
public class ListProjectCommand extends Command {

    private static final String MAIN_COMMAND_WORD = "list-project";
    private static final String ALIAS_COMMAND_WORD = "lp";
    private static final Set<String> COMMAND_WORDS = new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));
    
    public static final String MESSAGE_SUCCESS = "Listed all projects";

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        //model.updateSortedProjctList(); // will fill in upon sorting being merged in
        return new CommandResult(MESSAGE_SUCCESS, ListType.PROJECT);
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }
}
