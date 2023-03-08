package seedu.address.logic.commands.drugcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.drug.Drug;
import seedu.address.model.drug.TradeName;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRADE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE;


public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    public static final String MESSAGE_SUCCESS = "Drug: %1$s \nUpdated storage count: %2$s";
    public static final String MESSAGE_FAILURE = "Drug not found: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates storage count of drug whose trade name matches given trade name.\n"
            + "Parameters: "
            + PREFIX_TRADE_NAME + " TRADENAME"
            + PREFIX_UPDATE + " UPDATE VALUE (add + or - in front for addition/deduction) \n"
            + "Example: " + COMMAND_WORD
            + PREFIX_TRADE_NAME + " Panadol"
            + PREFIX_UPDATE + "+30";

    private final TradeName tradeName;
    private final Integer value;
    private final boolean add;

    public UpdateCommand(TradeName tradeName, Integer value, boolean add) {
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
}
