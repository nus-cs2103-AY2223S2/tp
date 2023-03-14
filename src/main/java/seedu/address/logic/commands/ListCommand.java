package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import seedu.address.model.Model;

/**
 * Lists all pets in the pet list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all pets";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
