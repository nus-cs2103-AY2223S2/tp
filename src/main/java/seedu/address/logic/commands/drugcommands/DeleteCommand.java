package seedu.address.logic.commands.drugcommands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.drug.Drug;
import seedu.address.model.drug.TradeName;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
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
            + PREFIX_INDEX + "INDEX"
            + "Example: " + COMMAND_WORD + " 2"
            + "\nOR\n"
            + COMMAND_WORD + ":  Deletes the drug identified by its trade name.\n"
            + "Parameters: "
            + PREFIX_TRADE_NAME + "TRADE NAME"
            + "Example: " + COMMAND_WORD + " Panadol ";

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
