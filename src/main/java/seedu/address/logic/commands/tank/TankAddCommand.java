package seedu.address.logic.commands.tank;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tank.Tank;

/**
 * Parses input arguments and creates a new TankAddCommand object
 */
public class TankAddCommand extends TankCommand {
    public static final String TANK_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TANK_COMMAND_WORD
            + ": Adds a Tank to Fish Ahoy!. \n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " " + TANK_COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Saltwater Tank 1 ";

    public static final String MESSAGE_SUCCESS = "New Tank added: %1$s";
    public static final String MESSAGE_DUPLICATE_TANK = "This Tank already exists in Fish Ahoy!";

    private final Tank toAdd;

    /**
     * Constructs a {@code TankAddCommand} with the {@code Tank} to be added to the {@code TankList}.
     */
    public TankAddCommand(Tank tank) {
        requireNonNull(tank);
        toAdd = tank;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTank(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TANK);
        }
        toAdd.getReadingLevels().setTank(toAdd);
        model.addTank(toAdd);
        model.addIndividualReadingLevels(toAdd.getReadingLevels());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TankAddCommand // instanceof handles nulls
                && toAdd.equals(((TankAddCommand) other).toAdd));
    }
}
