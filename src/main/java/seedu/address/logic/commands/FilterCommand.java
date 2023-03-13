package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ContactContainsTagPredicate;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters a contact.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Examples:\n" + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe\n"
            + COMMAND_WORD + " "
            + PREFIX_PHONE + "98765432\n"
            + COMMAND_WORD + " "
            + PREFIX_EMAIL + "johnd@example.com\n"
            + COMMAND_WORD + " "
            + PREFIX_ADDRESS + "My favourite TA\n"
            + COMMAND_WORD + " "
            + PREFIX_TAG + "Friends "
            + PREFIX_TAG + "Family";



    public static final String MESSAGE_SUCCESS = "%1$s contact matches the tag!";

    private final Predicate<Person> predicate;

    public FilterCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(this.predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
