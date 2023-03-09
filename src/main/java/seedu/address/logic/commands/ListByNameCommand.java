package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book by their names to the user.
 */
public class ListByNameCommand extends Command {
    public static final String COMMAND_WORD = "list_name";
    public static final String MESSAGE_SUCCESS = "Listed all persons by their names";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonListByName(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
