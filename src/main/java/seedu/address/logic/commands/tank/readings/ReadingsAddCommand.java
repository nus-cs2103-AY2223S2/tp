package seedu.address.logic.commands.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMMONIA_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tank.TankCommand;
import seedu.address.model.Model;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.AmmoniaLevel;

/**
 * Parses input arguments and creates a new ReadingsAddCommand object
 */
public class ReadingsAddCommand {//extends TankCommand {
//    public static final String TANK_COMMAND_WORD = "addReadings";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TANK_COMMAND_WORD
//            + ": Adds a set of readings to a tank. \n"
//            + "Parameters: "
//            + PREFIX_TANK + "INDEX \n"
//            + PREFIX_AMMONIA_LEVEL + "AMMONIA LEVEL \n"
//            + "Example: " + COMMAND_WORD + " " + TANK_COMMAND_WORD + " "
//            + PREFIX_TANK + "1 "
//            + PREFIX_AMMONIA_LEVEL + "1.2 ";
//
//    public static final String MESSAGE_SUCCESS = "New ammonia level reading added: %1$s";
//    public static final String MESSAGE_MISSING_TANK = "The tank index specified does not exist";
//
//    private final AmmoniaLevel toAdd;
//    private final Index tankIndex;
//
//    /**
//     * Creates a ReadingsAddCommand to add the specified {@code readings}
//     */
//    public ReadingsAddCommand(AmmoniaLevel ammoniaLevel, Index tankIndex) {
//        requireNonNull(ammoniaLevel);
//        requireNonNull(tankIndex);
//        toAdd = ammoniaLevel;
//        this.tankIndex = tankIndex;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//
//        Tank tank;
//        try {
//            tank = model.getFilteredTankList().get(tankIndex.getZeroBased());
//        } catch (IndexOutOfBoundsException e) {
//            throw new CommandException(MESSAGE_MISSING_TANK);
//        }
//        // check that tank is non-null
//        requireNonNull(tank);
//        // assigns fish to tank
//        toAdd.
//        //each tank has individual fishlist/addressbook
//        tank.addFish(toAdd);
//
//        model.addFish(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
//    }
}
