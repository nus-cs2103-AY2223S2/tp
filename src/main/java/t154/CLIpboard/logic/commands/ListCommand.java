package t154.CLIpboard.logic.commands;

import static java.util.Objects.requireNonNull;
import static t154.CLIpboard.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import t154.CLIpboard.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
