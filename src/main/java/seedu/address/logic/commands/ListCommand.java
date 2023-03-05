package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ELDERLY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PAIR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VOLUNTEER;

import seedu.address.model.Model;

/**
 * Lists all elderly, volunteers and pairs in FriendlyLink.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all elderly, volunteers and pairs";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredElderlyList(PREDICATE_SHOW_ALL_ELDERLY);
        model.updateFilteredVolunteerList(PREDICATE_SHOW_ALL_VOLUNTEER);
        model.updateFilteredPairList(PREDICATE_SHOW_ALL_PAIR);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
