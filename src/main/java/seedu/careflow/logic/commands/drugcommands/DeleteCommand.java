package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_TRADE_NAME;

import java.util.List;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.TradeName;

/**
 * Deletes a drug from the drug inventory
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Deleted Drug: %1$s";
    public static final String MESSAGE_FAILURE = "Drug not found: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":  Deletes the drug identified by the index number used in the displayed drug list.\n"
            + "Parameters: "
            + PREFIX_INDEX + " INDEX\n"
            + "Example: " + COMMAND_WORD + " -i 2"
            + "\nOR\n"
            + COMMAND_WORD + ":  Deletes the drug identified by its trade name.\n"
            + "Parameters: "
            + PREFIX_TRADE_NAME + " TRADE NAME\n"
            + "Example: " + COMMAND_WORD + " -tn Panadol";

    private Index targetIndex;
    private TradeName targetTradeName;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public DeleteCommand(TradeName targetTradeName) {
        this.targetTradeName = targetTradeName;
    }

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        List<Drug> drugList = model.getFilteredDrugList();
        if (targetTradeName == null) {
            if (targetIndex.getZeroBased() >= drugList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DRUG_DISPLAYED_INDEX);
            }
            Drug drugToDelete = drugList.get(targetIndex.getZeroBased());
            model.deleteDrug(drugToDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, drugToDelete));
        } else {
            Drug drugToDelete = null;
            for (Drug drug : drugList) {
                if (drug.getTradeName().equals(targetTradeName)) {
                    drugToDelete = drug;
                    break;
                }
            }
            if (drugToDelete == null) {
                throw new CommandException(String.format(MESSAGE_FAILURE, targetTradeName));
            }
            model.deleteDrug(drugToDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, drugToDelete));
        }
    }
}
