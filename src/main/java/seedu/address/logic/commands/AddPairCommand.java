package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.information.Nric;

/**
 * Adds a person to FriendlyLink.
 */
public class AddPairCommand extends Command {

    public static final String COMMAND_WORD = "add_pair";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pairs an elderly and volunteer in FriendlyLink. "
            + "Parameters: "
            + PREFIX_NRIC_ELDERLY + "ELDERLY ID "
            + PREFIX_NRIC_VOLUNTEER + "VOLUNTEER ID "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC_ELDERLY + "1234 "
            + PREFIX_NRIC_VOLUNTEER + "5678 ";

    public static final String MESSAGE_SUCCESS = "New pair added: %1$s";

    public static final String MESSAGE_DUPLICATE_PAIR = "This pair already exists in FriendlyLink";

    public static final String MESSAGE_ELDERLY_NOT_FOUND =
            "The elderly with the specified NRIC does not exist in FriendlyLink";

    public static final String MESSAGE_VOLUNTEER_NOT_FOUND =
            "The volunteer with the specified NRIC does not exist in FriendlyLink";

    private final Nric elderlyNric;
    private final Nric volunteerNric;

    /**
     * Creates an AddPairCommand to add the specified {@code Pair}
     */
    public AddPairCommand(Nric elderlyNric, Nric volunteerNric) {
        requireAllNonNull(elderlyNric, volunteerNric);
        this.elderlyNric = elderlyNric;
        this.volunteerNric = volunteerNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Elderly elderly;
        Volunteer volunteer;

        try {
            elderly = model.getElderly(elderlyNric);
        } catch (PersonNotFoundException e) {
            throw new CommandException(MESSAGE_ELDERLY_NOT_FOUND);
        }

        try {
            volunteer = model.getVolunteer(volunteerNric);
        } catch (PersonNotFoundException e) {
            throw new CommandException(MESSAGE_VOLUNTEER_NOT_FOUND);
        }

        Pair toAdd = new Pair(elderly, volunteer);
        if (model.hasPair(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PAIR);
        }

        model.addPair(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPairCommand // instanceof handles nulls
                && elderlyNric.equals(((AddPairCommand) other).elderlyNric)
                && volunteerNric.equals(((AddPairCommand) other).volunteerNric));
    }
}
