package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NRIC_NOT_EXIST;

import java.util.HashMap;
import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.VolunteerNotFoundException;
import seedu.address.model.person.information.Nric;

/**
 * Deletes a volunteer identified using their NRIC, from the FriendlyLink database.
 */
public class DeleteVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "delete_volunteer";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new HashMap<>();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the volunteer identified by their NRIC.\n"
            + "Parameters: <NRIC> \n"
            + "Example: " + COMMAND_WORD + " S1234567I";

    public static final String MESSAGE_DELETE_VOLUNTEER_SUCCESS = "Deleted Volunteer: %1$s";

    private final Nric targetNric;

    /**
     * Constructs a DeleteElderlyCommand to delete an elderly.
     *
     * @param targetNric Nric of the elderly.
     */
    public DeleteVolunteerCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Volunteer volunteerToDelete = model.getVolunteer(targetNric);
            model.deleteVolunteer(volunteerToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_VOLUNTEER_SUCCESS, volunteerToDelete));
        } catch (VolunteerNotFoundException e) {
            throw new CommandException(MESSAGE_NRIC_NOT_EXIST);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteVolunteerCommand // instanceof handles nulls
                && targetNric.equals(((DeleteVolunteerCommand) other).targetNric)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetNric);
    }
}
