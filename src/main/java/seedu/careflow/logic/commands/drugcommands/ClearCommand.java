package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.DrugInventory;

/**
 * Empties the existing Drug inventory
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Drug inventory has been cleared!";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        model.setDrugInventory(new DrugInventory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}


