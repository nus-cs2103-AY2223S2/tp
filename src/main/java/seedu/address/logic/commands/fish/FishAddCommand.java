package seedu.address.logic.commands.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDING_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;

/**
 * Parses input arguments and creates a new FishAddCommand object
 */
public class FishAddCommand extends FishCommand {
    public static final String FISH_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a fish to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LAST_FED_DATE + "LAST FED DATE "
            + PREFIX_SPECIES + "SPECIES "
            + PREFIX_FEEDING_INTERVAL + "FEEDING INTERVAL "
            + PREFIX_TANK + "TANK INDEX"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_LAST_FED_DATE + "01/01/2023 "
            + PREFIX_SPECIES + "Guppy "
            + PREFIX_FEEDING_INTERVAL + "0d5h "
            + PREFIX_TANK + "1"
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New fish added: %1$s";
    public static final String MESSAGE_DUPLICATE_FISH = "This fish already exists in the address book";
    public static final String MESSAGE_MISSING_TANK = "The tank index specified does not exist";

    private final Fish toAdd;
    private final Index tankIndex;

    /**
     * Creates an AddCommand to add the specified {@code Fish}
     */
    public FishAddCommand(Fish fish, Index tankIndex) {
        requireNonNull(fish);
        toAdd = fish;
        this.tankIndex = tankIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFish(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FISH);
        }

        Tank tank;
        try {
            tank = model.getFilteredTankList().get(tankIndex.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_MISSING_TANK);
        }
        // check that tank is non-null
        requireNonNull(tank);
        // assigns fish to tank
        toAdd.setTank(tank);

        model.addFish(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FishAddCommand // instanceof handles nulls
                && toAdd.equals(((FishAddCommand) other).toAdd));
    }
}
