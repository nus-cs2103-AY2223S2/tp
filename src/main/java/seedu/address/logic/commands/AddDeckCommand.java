package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DECK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Deck;

/**
 * Adds a card to the address book.
 */
public class AddDeckCommand extends Command {

    public static final String COMMAND_WORD = "addDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new deck "
            + "Parameters: "
            + PREFIX_DECK + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DECK + "LAK1201";

    public static final String MESSAGE_SUCCESS = "New deck created: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This deck already exists in the deck list";

    private final Deck toCreate;

    /**
     * Creates an AddCommand to add the specified {@code Card}
     */
    public AddDeckCommand(Deck deck) {
        requireNonNull(deck);
        toCreate = deck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /*
        if (model.hasDeck(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
         */

        model.createDeck(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate.getDeckName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeckCommand // instanceof handles nulls
                && toCreate.equals(((AddDeckCommand) other).toCreate));
    }
}
