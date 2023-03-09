package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model1.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.address.model.Model1;

/**
 * Lists all internships saved in TinS to the user.
 */
public class ListCommand1 extends Command1 {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all internships";


    @Override
    public CommandResult execute(Model1 model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
