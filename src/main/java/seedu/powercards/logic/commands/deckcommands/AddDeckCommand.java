package seedu.powercards.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.deck.Deck;

/**
 * Adds a card to the master deck.
 */
public class AddDeckCommand extends Command {

    public static final String COMMAND_WORD = "addDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new deck "
            + "Parameters: "
            + "DECK NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "LAK1201";

    public static final String MESSAGE_SUCCESS = "New deck created: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck name already exists.";

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
