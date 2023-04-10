package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NRIC_NOT_EXIST;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.exceptions.ElderlyNotFoundException;
import seedu.address.model.person.information.Nric;

/**
 * Deletes an elderly identified using their NRIC, from the FriendlyLink database.
 */
public class DeleteElderlyCommand extends Command {

    public static final String COMMAND_WORD = "delete_elderly";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the elderly identified by their NRIC.\n"
            + "Parameters: <NRIC> \n"
            + "Example: " + COMMAND_WORD + " S1234567C";

    public static final String MESSAGE_DELETE_ELDERLY_SUCCESS = "Deleted Elderly: %1$s";

    private final Nric targetNric;

    /**
     * Constructs a DeleteElderlyCommand to delete an elderly.
     *
     * @param targetNric Nric of the elderly.
     */
    public DeleteElderlyCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Elderly elderlyToDelete = model.getElderly(targetNric);
            model.deleteElderly(elderlyToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_ELDERLY_SUCCESS, elderlyToDelete));
        } catch (ElderlyNotFoundException e) {
            throw new CommandException(MESSAGE_NRIC_NOT_EXIST);
        }
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
