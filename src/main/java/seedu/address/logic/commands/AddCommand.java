package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(
            PREFIX_NAME,
            PREFIX_PHONE.asOptional(),
            PREFIX_EMAIL.asOptional(),
            PREFIX_ADDRESS.asOptional(),
            PREFIX_EDUCATION.asOptional(),
            PREFIX_TELEGRAM.asOptional(),
            PREFIX_REMARK.asOptional(),
            PREFIX_TAG.asOptional().asRepeatable(),
            PREFIX_MODULE.asOptional().asRepeatable()
    ));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + ARGUMENT_PREFIXES.stream()
                    .map(Prefix::toString)
                    .collect(Collectors.joining(" "))
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME.getPrefix() + "John Doe "
            + PREFIX_PHONE.getPrefix() + "98765432 "
            + PREFIX_EMAIL.getPrefix() + "johnd@example.com "
            + PREFIX_ADDRESS.getPrefix() + "311, Clementi Ave 2, #02-25 "
            + PREFIX_EDUCATION.getPrefix() + "P6 "
            + PREFIX_REMARK.getPrefix() + "Needs help with algebra "
            + PREFIX_TAG.getPrefix() + "friends "
            + PREFIX_TAG.getPrefix() + "owesMoney "
            + PREFIX_MODULE.getPrefix() + "CS2103T "
            + PREFIX_TELEGRAM.getPrefix() + "@johndtele";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.commitAddressBook(COMMAND_WORD);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
