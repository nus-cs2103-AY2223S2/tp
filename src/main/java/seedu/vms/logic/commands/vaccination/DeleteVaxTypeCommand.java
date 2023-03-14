package seedu.vms.logic.commands.vaccination;

import java.util.Objects;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;


/**
 * Command to delete a vaccination type.
 */
public class DeleteVaxTypeCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Vaccination type successfully deleted: %s";

    private final GroupName vaxName;


    /**
     * Constructs a {@code DeleteVaxTypeCommand}.
     */
    public DeleteVaxTypeCommand(GroupName vaxName) {
        this.vaxName = Objects.requireNonNull(vaxName);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        Objects.requireNonNull(model);

        try {
            VaxType vaxType = model.deleteVaxType(vaxName);
            return new CommandResult(String.format(MESSAGE_SUCCESS, vaxType.toString()));
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage(), ive);
        }
    }
}
