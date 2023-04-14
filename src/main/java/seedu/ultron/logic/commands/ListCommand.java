package seedu.ultron.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.model.Model.PREDICATE_SHOW_ALL_OPENINGS;

import seedu.ultron.model.Model;

/**
 * Lists all openings Ultron to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all openings.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        model.setSelectedIndex(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
