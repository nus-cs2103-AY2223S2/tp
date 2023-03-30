package seedu.vms.logic.commands.vaccination;

import java.util.Objects;

import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.LimitExceededException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeBuilder;


/**
 * Command to add a vaccination type.
 */
public class AddVaxTypeCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Vaccination: %s";

    private final GroupName name;
    private final VaxTypeBuilder builder;


    /**
     * Constructs an {@code AddVaxTypeCommand}.
     */
    public AddVaxTypeCommand(GroupName name, VaxTypeBuilder builder) {
        this.name = name;
        this.builder = Objects.requireNonNull(builder);
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        Objects.requireNonNull(model);

        try {
            VaxType vaxType = builder.create(name);
            ValueChange<VaxType> change = model.addVaccination(vaxType);
            return new CommandMessage(String.format(MESSAGE_SUCCESS, change.toString()));
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage(), ive);
        } catch (LimitExceededException limitEx) {
            throw new CommandException(String.format("Vaccination: %s", limitEx.getMessage()));
        }
    }
}
