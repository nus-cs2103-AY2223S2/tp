package seedu.vms.logic.commands.vaccination;

import seedu.vms.commons.core.Retriever;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;


/**
 * Command to show the details of a vaccination.
 */
public class DetailVaxTypeCommand extends Command {
    private final Retriever<String, VaxType> retriever;


    /**
     * Constructs a {@code DetailVaxTypeCommand}.
     */
    public DetailVaxTypeCommand(Retriever<String, VaxType> retriever) {
        this.retriever = retriever;
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        VaxType vaxType;
        try {
            vaxType = model.getVaccination(retriever);
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage());
        }
        model.setDetailedVaxType(vaxType);
        return new CommandMessage(String.format("Detailing vaccination: %s", vaxType.getName()));
    }
}
