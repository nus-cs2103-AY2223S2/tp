package arb.logic.commands.project;

import static arb.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static java.util.Objects.requireNonNull;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Lists all projects in the address book to the user.
 */
public class ListProjectCommand extends Command {

    public static final String COMMAND_WORD = "list-project";

    public static final String MESSAGE_SUCCESS = "Listed all projects";


    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        //model.updateSortedProjctList(); // will fill in upon sorting being merged in
        return new CommandResult(MESSAGE_SUCCESS, ListType.PROJECT);
    }
}
