package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.ui.Ui;

/**
 * Lists all drugs in the drug inventory
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all drugs";

    @Override
    public CommandResult execute(Ui ui, CareFlowModel model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredDrugList(model.PREDICATE_SHOW_ALL_DRUGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
