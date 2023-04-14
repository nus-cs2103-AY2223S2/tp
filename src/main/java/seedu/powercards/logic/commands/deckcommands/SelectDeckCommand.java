package seedu.powercards.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.deck.Deck;


/**
 * Selects a deck to operate on.
 */
public class SelectDeckCommand extends Command {

    public static final String COMMAND_WORD = "selectDeck";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select a deck of cards "
            + "by the index number used in the displayed deck list.\n"
            + "Parameter: INDEX (must be a positive integer).\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deck selected: %1$s"; // %1$s is the first argument in format
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "Deck index provided is invalid";
    private final Index deckIndex;

    /**
     * Creates an AddCardCommand to add the specified {@code Card}
     */
    public SelectDeckCommand(Index idx) {
        requireNonNull(idx);
        this.deckIndex = idx;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Deck> deckList = model.getFilteredDeckList();
        boolean isIndexOutOfBound = deckIndex.getZeroBased() >= deckList.size();
        if (isIndexOutOfBound) {
            throw new CommandException(MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        model.selectDeck(deckIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getSelectedDeckName()),
                false, false, false, false, true, false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectDeckCommand // instanceof handles nulls
                && deckIndex.equals(((SelectDeckCommand) other).deckIndex));
    }
}
