package seedu.careflow.logic.commands.drugcommands;


import static java.util.Objects.requireNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.careflow.logic.parser.drugparser.DrugParser.OPERATION_TYPE;

import java.util.List;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.drug.Drug;

/**
 * Views a drug from the drug records
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = OPERATION_TYPE + " " + COMMAND_WORD
            + ": Views the drug identified by the index number used in the displayed drug list.\n"
            + "Parameters: "
            + PREFIX_INDEX + " INDEX (must be a positive integer)\n"
            + "Example: " + OPERATION_TYPE + " " + COMMAND_WORD + " -i 1";

    public static final String MESSAGE_VIEW_DRUG_SUCCESS = "Viewed drug: %1$s";

    private Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof seedu.careflow.logic.commands.drugcommands.ViewCommand) {
            if (targetIndex != null) {
                return targetIndex.equals((
                        (seedu.careflow.logic.commands.drugcommands.ViewCommand)
                                other).targetIndex);
            }
        }
        return other == this;
    }

    /**
     *  Executes the Drug deletion
     * @param model {@code Model} which the command should operate on.
     * @return The command result if deletion is successful
     * @throws CommandException If an error occurred during deletion
     */
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        List<Drug> drugList = model.getFilteredDrugList();
        if (targetIndex.getZeroBased() >= drugList.size() || targetIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_DRUG_DISPLAYED_INDEX);
        } else {
            Drug drugToView = drugList.get(targetIndex.getZeroBased());
            return new CommandResult(drugToView, String.format(MESSAGE_VIEW_DRUG_SUCCESS, drugToView));
        }
    }
}
