package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * Adds a card to the address book.
 */
public class AddDeckCommand extends Command {

    public static final String COMMAND_WORD = "addDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new deck "
            + "Parameters: "
            + "DECK NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "LAK1201";

    public static final String MESSAGE_SUCCESS = "New deck created: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck already exists in the deck list";

    private final Deck toAdd;

    /**
     * Creates an AddDeckCommand to add the specified {@code Deck}
     */
    public AddDeckCommand(Deck deck) {
        requireNonNull(deck);
        toAdd = deck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDeck(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        model.addDeck(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDeckName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeckCommand // instanceof handles nulls
                && toAdd.equals(((AddDeckCommand) other).toAdd));
    }
}
