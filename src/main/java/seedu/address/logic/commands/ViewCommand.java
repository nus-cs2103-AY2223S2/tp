package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LISTINGS;

import seedu.address.model.Model;

/**
 * Lists all listings in the listing book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Listed all listings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredListingList(PREDICATE_SHOW_ALL_LISTINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

