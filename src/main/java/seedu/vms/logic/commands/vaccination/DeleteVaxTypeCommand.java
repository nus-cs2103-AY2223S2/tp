package seedu.vms.logic.commands.vaccination;

import java.util.Objects;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.Retriever;
import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.UnexpectedChangeException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;


/**
 * Command to delete a vaccination type.
 */
public class DeleteVaxTypeCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Vaccination: %s";

    private final Retriever<String, VaxType> retriever;
    private final boolean isForce;


    /**
     * Constructs a {@code DeleteVaxTypeCommand}.
     */
    public DeleteVaxTypeCommand(Retriever<String, VaxType> retriever, boolean isForce) {
        this.retriever = Objects.requireNonNull(retriever);
        this.isForce = isForce;
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        Objects.requireNonNull(model);

        try {
            VaxType vaxType = model.getVaccination(retriever);
            ValueChange<VaxType> change = model.deleteVaccination(vaxType.getGroupName(), isForce);
            return new CommandMessage(String.format(MESSAGE_SUCCESS, change.toString()));
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage(), ive);
        } catch (UnexpectedChangeException uce) {
            throw new CommandException(String.format("%s\n%s",
                    uce.getMessage(),
                    Messages.MESSAGE_USE_FORCE));
        }
    }
}
