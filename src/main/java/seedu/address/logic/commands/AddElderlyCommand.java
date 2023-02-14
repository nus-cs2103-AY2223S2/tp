package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;


/**
 * Adds an elderly to the database.
 */
public class AddElderlyCommand extends Command {

    // later find ways to make this "add elderly"
    public static final String COMMAND_WORD = "add_elderly";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an elderly to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_AGE + "AGE "
            + PREFIX_RISK + "MEDICAL RISK (LOW, MEDIUM or HIGH) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_AGE + "69 "
            + PREFIX_RISK + "LOW "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New elderly added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This elderly already exists in the database";

    private final Elderly toAdd;

    /**
     * Creates an AddElderlyCommand to add to the specified {@code Elderly}
     */
    public AddElderlyCommand(Elderly elderly) {
        requireNonNull(elderly);
        toAdd = elderly;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // hasPerson makes the judgement based on if same name
        // in Elderly, criteria is same name and age
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddElderlyCommand // instanceof handles nulls
                && toAdd.equals(((AddElderlyCommand) other).toAdd));
    }
}
