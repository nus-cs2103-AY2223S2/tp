package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
// import static seedu.address.model.ModelNew.PREDICATE_SHOW_ALL_OPENINGS;

import seedu.address.model.Model;

/**
 * Lists all openings in the address book to the user.
 */

public class NewListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all openings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

//    @Override
//    public CommandResult execute(ModelNew model) {
//        requireNonNull(model);
//        // model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
//        return new CommandResult(MESSAGE_SUCCESS);
//    }
}
