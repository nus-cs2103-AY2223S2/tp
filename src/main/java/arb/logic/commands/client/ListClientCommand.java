package arb.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static arb.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
