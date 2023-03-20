package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Nric;

/**
 * Deletes an elderly identified using its NRIC from the FriendlyLink database.
 */
public class DeleteElderlyCommand extends Command {

    public static final String COMMAND_WORD = "delete_elderly";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the elderly identified by their NRIC.\n"
            + "Parameters: NRIC \n"
            + "Example: " + COMMAND_WORD + " S1234567C";

    public static final String MESSAGE_INVALID_NRIC_ELDERLY =
            String.format(Messages.MESSAGE_INVALID_NRIC, "elderly");

    public static final String MESSAGE_DELETE_ELDERLY_SUCCESS = "Deleted Elderly: %1$s";

    private final Nric targetNric;

    public DeleteElderlyCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Elderly elderlyToDelete = model.getElderly(targetNric);
        if (elderlyToDelete == null) {
            throw new CommandException(Messages.MESSAGE_NRIC_NOT_EXIST);
        }
        model.deleteElderly(elderlyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ELDERLY_SUCCESS, elderlyToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteElderlyCommand
                && targetNric.equals(((DeleteElderlyCommand) other).targetNric));
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetNric);
    }
}
