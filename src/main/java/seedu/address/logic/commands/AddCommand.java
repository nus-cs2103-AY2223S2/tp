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
            PREFIX_NAME.setExamples("John Doe"),
            PREFIX_PHONE.asOptional().setExamples("98765432"),
            PREFIX_EMAIL.asOptional().setExamples("johnd@example.com"),
            PREFIX_ADDRESS.asOptional().setExamples("311, Clementi Ave 2, #02-25"),
            PREFIX_EDUCATION.asOptional().setExamples("Year 1"),
            PREFIX_TELEGRAM.asOptional().setExamples("@johndtele"),
            PREFIX_REMARK.asOptional().setExamples("Have submitted tutorial worksheet for Week 10"),
            PREFIX_TAG.asOptional().asRepeatable().setExamples("Tut1", "hasSubmitted"),
            PREFIX_MODULE.asOptional().asRepeatable().setExamples("CS2103T")
    ));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book."
            + "\n" + getParameterUsage(ARGUMENT_PREFIXES)
            + "\n" + getExampleUsage(COMMAND_WORD, ARGUMENT_PREFIXES);

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
