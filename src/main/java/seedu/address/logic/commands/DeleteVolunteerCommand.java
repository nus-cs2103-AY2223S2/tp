package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.information.Nric;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "delete_volunteer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the volunteer identified by their NRIC.\n"
            + "Parameters: NRIC \n"
            + "Example: " + COMMAND_WORD + " S1234567I";

    public static final String MESSAGE_DELETE_SUCCESS = "Deleted Volunteer: %1$s";

    private final Nric targetNric;

    public DeleteVolunteerCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);
            Volunteer volunteerToDelete = model.getVolunteer(targetNric);
            model.deleteVolunteer(volunteerToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, volunteerToDelete));
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_VOLUNTEER_NRIC);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteVolunteerCommand // instanceof handles nulls
                && targetNric.equals(((DeleteVolunteerCommand) other).targetNric)); // state check
    }
}
