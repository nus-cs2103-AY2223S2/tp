package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose details contains any of the
 * argument keywords based on the prefixes in the user input.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose details contain any of "
            + "the specified keywords (case-insensitive) based on "
            + "the prefix provided and displays them as a list with index numbers.\n"
            + "Each prefix must be followed by one and only one keyword.\n"
            + "Please use the \"help\" command for more information on "
            + "the usage of this command.\n"
            + "Parameters: [PREFIX]/KEYWORD [MORE [PREFIX]/KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " n/alice s/y4 p/91234567"
            + " e/alice@example.com a/blk 123 t/cs2103";

    private Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.findOrListContents(predicate, "find");
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
