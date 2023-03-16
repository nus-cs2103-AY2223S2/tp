package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.experimental.model.Model.PREDICATE_SHOW_ALL_ENTITIES;

import seedu.address.experimental.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
