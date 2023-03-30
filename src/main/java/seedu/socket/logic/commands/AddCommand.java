package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.person.Person;

/**
 * Adds a person to SOCket.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to SOCket.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PROFILE + "GITHUBPROFILE] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_LANGUAGE + "LANGUAGE]... "
            + "[" + PREFIX_TAG + "TAG]... (restrict to 10 tags)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PROFILE + "john-doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_LANGUAGE + "Java "
            + PREFIX_LANGUAGE + "JavaScript "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in SOCket";
    public static final String MESSAGE_EXCEED_TAG = "There are %1$s tags which exceeds the limit of 10 tags";

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

        int tagSize = toAdd.getTags().size();

        if (tagSize > 10) {
            throw new CommandException(String.format(MESSAGE_EXCEED_TAG, tagSize));
        }
        model.addPerson(toAdd);
        model.commitSocket();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
