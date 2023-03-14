package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;

/**
 * Adds a fish to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a fish to the address book. "
            + "Parameters: "
            + PREFIX_TANK + "TANKINDEX "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TANK + "1"
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New fish added: %1$s";
    public static final String MESSAGE_DUPLICATE_FISH = "This fish already exists in the address book";
    public static final String MESSAGE_MISSING_TANK = "The tank index specified does not exist";

    private final Fish toAdd;
    private final Index tankIndex;

    /**
     * Creates an AddCommand to add the specified {@code Fish}
     */
    public AddCommand(Fish fish, Index tankIndex) {
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
            tank = model.getTankList().getTankList().get(tankIndex.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_MISSING_TANK);
        }
        // check that tank is non-null
        requireNonNull(tank);
        // assigns fish to tank
        tank.addFish(toAdd);
        model.addFish(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
