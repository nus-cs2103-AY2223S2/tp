package seedu.vms.logic.commands.vaccination;

import java.util.Objects;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Command to edit a vaccination type.
 */
public class EditVaxTypeCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Vaccination type successfully updated: %s";

    private final VaxTypeBuilder builder;


    /**
     * Constructs a {@code EditVaxTypeCommand}.
     *
     * @param builder - the builder to use to update the vaccination type.
     */
    public EditVaxTypeCommand(VaxTypeBuilder builder) {
        this.builder = Objects.requireNonNull(builder);
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        Objects.requireNonNull(model);

        try {
            VaxType vaxType = model.performVaxTypeAction(builder::update);
            return new CommandMessage(String.format(MESSAGE_SUCCESS, vaxType.toString()));
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage(), ive);
        }
    }
}
