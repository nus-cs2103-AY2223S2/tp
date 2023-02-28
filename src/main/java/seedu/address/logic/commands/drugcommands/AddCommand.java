package seedu.address.logic.commands.drugcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.drug.Drug;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVE_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIRECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIDE_EFFECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STORAGE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRADE_NAME;

/**
 * Adds a drug to the drug inventory
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":Adds a drug to the drug inventory. "
            + "Parameters: "
            + PREFIX_TRADE_NAME + "TRADE NAME "
            + PREFIX_ACTIVE_INGREDIENT + "ACTIVE INGREDIENT "
            + PREFIX_PURPOSE + "PURPOSE... "
            + PREFIX_SIDE_EFFECT + "SIDE EFFECT... "
            + PREFIX_DIRECTION + "DIRECTION"
            + PREFIX_EXPIRY_DATE + "EXPIRY DATE "
            + PREFIX_STORAGE_COUNT + "STORAGE COUNT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TRADE_NAME + "Panadol "
            + PREFIX_ACTIVE_INGREDIENT + "Paracetamol "
            + PREFIX_PURPOSE + "relieve pain "
            + PREFIX_PURPOSE + "relieve fever "
            + PREFIX_PURPOSE + "relieve headache "
            + PREFIX_SIDE_EFFECT + "skin rash "
            + PREFIX_SIDE_EFFECT + "swelling of the lips, tongue, throat or face "
            + PREFIX_SIDE_EFFECT + "nausea "
            + PREFIX_SIDE_EFFECT + "unexplained bruising or bleeding "
            + PREFIX_DIRECTION + "Adults and children over 12 years: 1-2 caplets taken every 4 to 6 hours. Not " +
            "recommended for children under 12 years. "
            + PREFIX_EXPIRY_DATE + "31-08-2025 "
            + PREFIX_STORAGE_COUNT + "50 ";

    public static final String MESSAGE_SUCCESS = "New drug added: %1$s";
    public static final String MESSAGE_DUPLICATE_DRUG = "This drug already exists in the drug inventory";

    public final Drug toAdd;

    public AddCommand(Drug drug) {
        requireNonNull(drug);
        toAdd = drug;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

    }
}
