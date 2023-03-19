package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_UNIT;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.Food;

/**
 * Adds a food to the Wife.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a food to WIFE. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_UNIT + "UNIT "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_EXPIRY_DATE + "EXPIRY DATE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Broccoli "
            + PREFIX_UNIT + "STALK "
            + PREFIX_QUANTITY + "2 "
            + PREFIX_EXPIRY_DATE + "03-03-2033 "
            + PREFIX_TAG + "VEGETABLES "
            + PREFIX_TAG + "HEALTHY";

    public static final String MESSAGE_SUCCESS = "New food added: %1$s";
    public static final String MESSAGE_DUPLICATE_FOOD = "This food item already exists in WIFE";

    private final Food toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Food food) {
        requireNonNull(food);
        toAdd = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFood(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.addFood(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
