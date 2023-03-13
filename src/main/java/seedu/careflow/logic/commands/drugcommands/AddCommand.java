package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_ACTIVE_INGREDIENT;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DIRECTION;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_SIDE_EFFECT;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_STORAGE_COUNT;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_TRADE_NAME;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.drug.Drug;


/**
 * Adds a drug to the drug inventory
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a drug to the drug inventory.\n"
            + "Parameters: "
            + PREFIX_TRADE_NAME + " TRADE NAME "
            + PREFIX_ACTIVE_INGREDIENT + " ACTIVE INGREDIENT "
            + PREFIX_PURPOSE + " PURPOSE(S)... "
            + PREFIX_SIDE_EFFECT + " SIDE EFFECT(S)... "
            + PREFIX_DIRECTION + " DIRECTION "
            + PREFIX_STORAGE_COUNT + " STORAGE COUNT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TRADE_NAME + " Panadol "
            + PREFIX_ACTIVE_INGREDIENT + " Paracetamol "
            + PREFIX_PURPOSE + " relieve pain / relieve fever / relieve headache "
            + PREFIX_SIDE_EFFECT + " skin rash / swelling of the lips, tongue, throat or face / nausea / "
            + "unexplained bruising or bleeding "
            + PREFIX_DIRECTION + " Adults and children over 12 years: 1-2 caplets taken every 4 to 6 hours. Not "
            + "recommended for children under 12 years. "
            + PREFIX_STORAGE_COUNT + " 50";

    public static final String MESSAGE_SUCCESS = "New drug added: %1$s";
    public static final String MESSAGE_DUPLICATE_DRUG = "This drug already exists in the drug inventory";

    public final Drug toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Drug}
     * @param drug the Drug to be added
     */
    public AddCommand(Drug drug) {
        requireNonNull(drug);
        toAdd = drug;
    }

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        if (model.hasDrug(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DRUG);
        }
        model.addDrug(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
