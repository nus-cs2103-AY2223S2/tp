package seedu.vms.logic.commands.vaccination;

import java.util.Objects;

import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;


/**
 * Command to delete a vaccination type.
 */
public class DeleteVaxTypeCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Vaccination: %s";

    private final GroupName vaxName;


    /**
     * Constructs a {@code DeleteVaxTypeCommand}.
     */
    public DeleteVaxTypeCommand(GroupName vaxName) {
        this.vaxName = Objects.requireNonNull(vaxName);
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        Objects.requireNonNull(model);

        try {
            ValueChange<VaxType> change = model.deleteVaccination(vaxName);
            return new CommandMessage(String.format(MESSAGE_SUCCESS, change.toString()));
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage(), ive);
        }
    }
}
