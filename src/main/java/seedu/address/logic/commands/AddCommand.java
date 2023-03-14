package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;

/**
 * Adds a fish to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a fish to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LAST_FED_DATE + "LAST FED DATE "
            + PREFIX_SPECIES + "SPECIES "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_LAST_FED_DATE + "98765432 "
            + PREFIX_SPECIES + "Guppy "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New fish added: %1$s";
    public static final String MESSAGE_DUPLICATE_FISH = "This fish already exists in the address book";

    private final Fish toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Fish}
     */
    public AddCommand(Fish fish) {
        requireNonNull(fish);
        toAdd = fish;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFish(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FISH);
        }

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
