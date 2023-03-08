package seedu.address.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DeckModel;
import seedu.address.model.powerdeck.PowerDeck;

public class SelectDeckCommand extends Command {
    public static final String COMMAND_WORD = "selectDeck";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select a deck of cards.";

    public static final String MESSAGE_SUCCESS = "Deck selected: %1$s"; // %1$s is the first argument in format
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "Deck index provided is invalid";
    private final Index deckIndex;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SelectDeckCommand(Index idx) {
        requireNonNull(idx);
        this.deckIndex = idx;
    }

    @Override
    public CommandResult execute(DeckModel model) throws CommandException {
        requireNonNull(model);

        List<PowerDeck> deckList = model.getDecks();
        boolean isIndexOutOfBound = deckIndex.getZeroBased() >= deckList.size();
        if (isIndexOutOfBound) {
            throw new CommandException(MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        model.selectDeck(deckIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getSelectedDeck()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectDeckCommand // instanceof handles nulls
                && deckIndex.equals(((SelectDeckCommand) other).deckIndex));
    }

}
