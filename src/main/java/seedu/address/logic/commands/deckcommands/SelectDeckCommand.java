package seedu.address.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Selects a deck to operate on.
 */
public class SelectDeckCommand { // Todo: Extends Command after Kok Hai refractors Model

    public static final String COMMAND_WORD = "selectDeck";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select a deck of cards.";

    public static final String MESSAGE_SUCCESS = "Deck selected: %1$s"; // %1$s is the first argument in format
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "Deck index provided is invalid";
    private final Index deckIndex;

    /**
     * Creates an AddCommand to add the specified {@code Card}
     */
    public SelectDeckCommand(Index idx) {
        requireNonNull(idx);
        this.deckIndex = idx;
    }

    // @Override
    /**
     * Executes the command and returns the result message. This javadoc message is here
     * to pass checkstyle. TODO: remove later
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /*
        List<Deck> deckList = model.getDecks(); // TODO implement getDecks in Model
        boolean isIndexOutOfBound = deckIndex.getZeroBased() >= deckList.size();
        if (isIndexOutOfBound) {
            throw new CommandException(MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        model.selectDeck(deckIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getSelectedDeck()));
         */
        return new CommandResult("test");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectDeckCommand // instanceof handles nulls
                && deckIndex.equals(((SelectDeckCommand) other).deckIndex));
    }

}
