package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_UPDATE_BY;
import static seedu.careflow.logic.parser.drugparser.DrugParser.OPERATION_TYPE;

import java.util.List;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.TradeName;

/**
 * Updates the storage count of an existing Drug in the drug inventory.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    public static final String MESSAGE_SUCCESS = "Drug: %1$s \nUpdated storage count: %2$s \nPlease select the drug "
            + "again to view changes if not displayed.";
    public static final String MESSAGE_FAILURE = "Drug not found: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates storage count of drug whose trade name matches given trade name.\n"
            + "Parameters: "
            + "TRADENAME "
            + PREFIX_UPDATE_BY + " AMOUNT (add + or - in front for addition/deduction) \n"
            + "Example: " + OPERATION_TYPE + " " + COMMAND_WORD
            + " Panadol "
            + PREFIX_UPDATE_BY + " +30";

    private final TradeName tradeName;
    private final Integer value;
    private final boolean add;

    /**
     * @param tradeName the tradeName keyword to identify the existing Drug to edit
     * @param value the update value in integer for storage count
     * @param add a boolean to denote addition or subtraction for the update value
     */
    public UpdateCommand(TradeName tradeName, Integer value, boolean add) {
        requireAllNonNull(tradeName, value, add);
        this.tradeName = tradeName;
        this.value = value;
        this.add = add;
    }

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        List<Drug> drugList = model.getFilteredDrugList();
        Drug toUpdate = null;
        for (Drug drug : drugList) {
            if (drug.getTradeName().equals(tradeName)) {
                if (add) {
                    drug.getStorageCount().incStorage(value);
                } else {
                    drug.getStorageCount().decrStorage(value);
                }
                toUpdate = drug;
                model.setDrug(drug, drug);
                break;
            }
        }
        if (toUpdate == null) {
            throw new CommandException(String.format(MESSAGE_FAILURE, tradeName));
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, toUpdate.getTradeName(), toUpdate.getStorageCount()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof UpdateCommand // instanceof handles nulls
                && tradeName.equals(((UpdateCommand) other).tradeName) // state check
                && add == (((UpdateCommand) other).add)
                && value.equals(((UpdateCommand) other).value);
    }
}
