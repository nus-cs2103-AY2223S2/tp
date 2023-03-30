package codoc.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import codoc.commons.core.Messages;
import codoc.model.Model;
import codoc.model.person.Person;

/**
 * Finds and lists all persons in CoDoc whose name contains any of the argument keywords.
 * Keyword matching is case- * insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the given constraints (case-insensitive) split by prefixes and displays them as a list with index "
            + "numbers.\n"
            + "Parameters: PREFIX/KEYWORD [MORE_KEYWORDS]... [PREFIX/KEYWORD [MORE_KEYWORDS]...]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie y/2 m/cs1101 cs1231";

    private final Predicate<Person> predicate;
    private final String userInputs;

    /**
     * @param predicate to filter out contacts
     * @param userInputs inputs that were used to create the predicate
     */
    public FindCommand(Predicate<Person> predicate, String userInputs) {
        this.predicate = predicate;
        this.userInputs = userInputs;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String appliedPredicates = model.updateFilteredPersonList(predicate, userInputs);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW.concat("\n").concat(appliedPredicates),
                        model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
