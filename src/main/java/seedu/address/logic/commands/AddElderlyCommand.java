package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;

/**
 * Adds an elderly to the database.
 */
public class AddElderlyCommand extends Command {

    public static final String COMMAND_WORD = "add_elderly";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an elderly to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_NRIC_ELDERLY + "NRIC "
            + PREFIX_AGE + "AGE "
            + PREFIX_RISK + "MEDICAL RISK (LOW, MEDIUM or HIGH) "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_AVAILABILITY + "START_DATE,END_DATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_NRIC_ELDERLY + "S1234567A "
            + PREFIX_AGE + "69 "
            + PREFIX_REGION + "WEST "
            + PREFIX_RISK + "LOW "
            + PREFIX_TAG + "diabetes "
            + PREFIX_TAG + "lonely"
            + PREFIX_AVAILABILITY + "2023-05-11 to 2023-05-12";

    public static final String MESSAGE_SUCCESS = "New elderly added: %1$s";

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
        if (model.hasElderly(toAdd)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_ELDERLY);
        }

        model.addElderly(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddElderlyCommand // instanceof handles nulls
                && toAdd.equals(((AddElderlyCommand) other).toAdd));
    }
}
