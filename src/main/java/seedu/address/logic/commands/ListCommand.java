package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.ui.result.ResultDisplay;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all contacts";
    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Lists all contacts in the address book.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD)
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "Note that the indices of the contacts in the resulting list may be different.");


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
