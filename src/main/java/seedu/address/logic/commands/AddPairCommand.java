package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VOLUNTEER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;

/**
 * Adds a person to the address book.
 */
public class AddPairCommand extends Command {

    public static final String COMMAND_WORD = "add_pair";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pairs an elderly and volunteer in the address book. "
            + "Parameters: "
            + PREFIX_ELDERLY + "ELDERLY ID "
            + PREFIX_VOLUNTEER + "VOLUNTEER ID "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ELDERLY + "1234 "
            + PREFIX_VOLUNTEER + "5678 ";

    public static final String MESSAGE_SUCCESS = "New pair added: %1$s";

    public static final String MESSAGE_DUPLICATE_PAIR = "This pair already exists in the address book";

    private final Pair toAdd;

    /**
     * Creates an AddPairCommand to add the specified {@code Pair}
     */
    public AddPairCommand(Pair pair) {
        requireNonNull(pair);
        toAdd = pair;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPair(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PAIR);
        }

        model.addPair(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPairCommand // instanceof handles nulls
                && toAdd.equals(((AddPairCommand) other).toAdd));
    }
}
