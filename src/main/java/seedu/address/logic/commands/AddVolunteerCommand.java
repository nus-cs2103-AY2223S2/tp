package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.LinkedHashMap;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Volunteer;

/**
 * Adds a volunteer to the database.
 */
public class AddVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "add_volunteer";

    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();

    static {
        COMMAND_PROMPTS.put(PREFIX_NAME, "<name>");
        COMMAND_PROMPTS.put(PREFIX_NRIC_VOLUNTEER, "<nric>");
        COMMAND_PROMPTS.put(PREFIX_ADDRESS, "<address>");
        COMMAND_PROMPTS.put(PREFIX_PHONE, "<phone>");
        COMMAND_PROMPTS.put(PREFIX_EMAIL, "<email>");
        COMMAND_PROMPTS.put(PREFIX_TAG, "<tag>");
        COMMAND_PROMPTS.put(PREFIX_REGION, "<region>");
        COMMAND_PROMPTS.put(PREFIX_AGE, "<age>");
        COMMAND_PROMPTS.put(PREFIX_AVAILABILITY, "<start_date,end_date>");
    }

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a volunteer to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_NRIC_VOLUNTEER + "NRIC "
            + PREFIX_AGE + "AGE "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_AVAILABILITY + "START_DATE,END_DATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_NRIC_VOLUNTEER + "S1234567A "
            + PREFIX_AGE + "20 "
            + PREFIX_REGION + "NORTH "
            + PREFIX_TAG + "new "
            + PREFIX_TAG + "undergradStudent"
            + PREFIX_AVAILABILITY + "2023-05-11 to 2023-05-12";

    public static final String MESSAGE_SUCCESS = "New volunteer added: %1$s";

    private final Volunteer toAdd;

    /**
     * Creates an AddVolunteerCommand to add to the specified {@code volunteer}
     */
    public AddVolunteerCommand(Volunteer volunteer) {
        requireNonNull(volunteer);
        toAdd = volunteer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasVolunteer(toAdd)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_VOLUNTEER);
        }

        model.addVolunteer(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVolunteerCommand // instanceof handles nulls
                && toAdd.equals(((AddVolunteerCommand) other).toAdd));
    }
}
