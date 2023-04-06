package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attribute"
            + " contain any of the specified keywords (case-insensitive)"
            + " and displays them as a list with\n"
            + " index numbers. Attribute is specified using n/, a/ or i/ representing name, address "
            + " and nric respectively\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC + "NRIC] "
            + "[" + PREFIX_DOCTOR + "DOCTOR] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " n/" + "alice" + "\n"
            + "Example: " + COMMAND_WORD + " a/" + "30 Serangoon" + "\n"
            + "Example: " + COMMAND_WORD + " i/" + "S0067812L" + "\n"
            + "Example: " + COMMAND_WORD + " n/" + "alice bob charlie" + "\n"
            + "Example: " + COMMAND_WORD + " ad/" + "Shannon" + "\n"
            + "Example: " + COMMAND_WORD + " t/" + "Diabetic" + "\n";

    public static final String MULTIPLE_PREFIX_MESSAGE = "Multiple attributes inputted. Only one attribute can be "
            + "filtered at a time";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
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
