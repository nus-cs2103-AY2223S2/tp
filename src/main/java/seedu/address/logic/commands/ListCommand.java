package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * Lists all elderly, volunteers and pairs in FriendlyLink.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();
    public static final String MESSAGE_SUCCESS = "Listed all elderly, volunteers and pairs";

    @Override
    @SuppressWarnings("unchecked")
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredElderlyList((Predicate<Elderly>) PREDICATE_SHOW_ALL);
        model.updateFilteredVolunteerList((Predicate<Volunteer>) PREDICATE_SHOW_ALL);
        model.updateFilteredPairList((Predicate<Pair>) PREDICATE_SHOW_ALL);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
