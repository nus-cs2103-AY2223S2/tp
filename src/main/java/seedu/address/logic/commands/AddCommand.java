package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.util.SampleDataUtil.SAMPLE_PERSON;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.result.ResultDisplay;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Adds a contact to the address book.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS,
                    PREFIX_NAME.toString("NAME"),
                    PREFIX_PHONE.toString("PHONE", true),
                    PREFIX_EMAIL.toString("EMAIL", true),
                    PREFIX_ADDRESS.toString("ADDRESS", true),
                    PREFIX_GENDER.toString("GENDER", true),
                    PREFIX_RACE.toString("RACE", true),
                    PREFIX_COMMS.toString("COMMUNICATION_CHANNEL", true),
                    PREFIX_MAJOR.toString("MAJOR", true),
                    PREFIX_FACULTY.toString("FACULTY", true),
                    PREFIX_MODULES.toString("MODULE", true, true),
                    PREFIX_TAG.toString("TAG", true, true))
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD,
                    PREFIX_NAME.toString(SAMPLE_PERSON.getName().toString()),
                    PREFIX_PHONE.toString(SAMPLE_PERSON.getPhone().toString()),
                    PREFIX_EMAIL.toString(SAMPLE_PERSON.getEmail().toString()),
                    PREFIX_ADDRESS.toString(SAMPLE_PERSON.getAddress().toString()),
                    PREFIX_TAG.toString("friends"),
                    PREFIX_TAG.toString("basketball"))
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "For more information on prefixes, refer to the user guide using the help command.");

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This contact already exists in the address book";

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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
