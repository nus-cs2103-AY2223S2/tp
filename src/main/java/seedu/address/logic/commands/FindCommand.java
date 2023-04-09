package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in HMHero whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords such as name(case-insensitive) or phone number displays them as a "
            + "list with index numbers.\n\n"
            + "Parameters: n/NAME p/PHONE NUMBER\n"
            + "Providing just one of name or phone prefix is sufficient.\n"
            + "Providing both name and phone prefixes narrows down the scope.\n\n"
            + "Example: " + COMMAND_WORD + " n/alice p/98752354";

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

    public Predicate<Person> getPredicate() {
        return findPredicate;
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
