package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords such as name, phone number, note (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: Format 1: KEYWORDS(either NAME or PHONE NUMBER)\n "
            + "Format 2: n/NAME p/PHONE NUMBER note/NOTE_1 note/NOTE_2\n"
            + "Any combination of prefixes are allowed for Format 2. Using more prefixes narrows down the target. \n"
            + "Example for Format 1: " + COMMAND_WORD + " alice bob charlie OR 12345678\n"
            + "Example for Format 2: " + COMMAND_WORD + "n/alice bob p/12345678";

    private final Predicate<Person> findPredicate;

    public FindCommand(Predicate<Person> findPredicate) {
        this.findPredicate = findPredicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(findPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FindCommand)) {
            return false;
        }
        FindCommand otherCmd = (FindCommand) other;
        return this.findPredicate.equals(otherCmd.findPredicate);
    }
}
