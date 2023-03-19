package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * Deletes a deck identified using its displayed index from the deck list.
 */
public class DeleteDeckCommand extends Command {

    public static final String COMMAND_WORD = "deleteDeck";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select a deck to delete "
            + "by the index number used in the displayed deck list.\n"
            + "Parameter: INDEX (must be a positive integer).\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deck deleted: %1$s"; // %1$s is the first argument in format
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "Deck index provided is invalid";
    private final Index deckIndex;

    /**
     * Creates a DeleteDeckCommand to delete the specified {@code Deck}
     */
    public DeleteDeckCommand(Index idx) {
        requireNonNull(idx);
        this.deckIndex = idx;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Deck> deckList = model.getFilteredDeckList();
        if (deckIndex.getZeroBased() >= deckList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck targetDeck = deckList.get(deckIndex.getZeroBased());
        model.removeDeck(targetDeck);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetDeck.getDeckName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDeckCommand // instanceof handles nulls
                && deckIndex.equals(((DeleteDeckCommand) other).deckIndex));
    }

}
