package seedu.powercards.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.deck.Deck;

/**
 * Edits the name of the deck
 */

public class EditDeckCommand extends Command {

    public static final String COMMAND_WORD = "editDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the name of the deck "
            + "by the index number used in the displayed deck list. "
            + "Parameters: INDEX (must be a positive integer) " + "DECK_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 " + "CS2107";

    public static final String MESSAGE_EDIT_DECK_SUCCESS = "Deck %1$s is successfully renamed to \"%2$s\".";

    public static final String MESSAGE_DUPLICATE_DECK = "This deck name already exists.";

    private final Index index;
    private final Deck editedDeck;

    /**
     * @param index      of the deck to edit
     * @param editedDeck edited deck of new name
     */
    public EditDeckCommand(Index index, Deck editedDeck) {
        requireNonNull(index);
        requireNonNull(editedDeck);

        this.index = index;
        this.editedDeck = editedDeck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Deck> lastShownList = model.getFilteredDeckList();
        boolean isIndexOutOfBound = index.getZeroBased() >= lastShownList.size();
        if (isIndexOutOfBound) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        if (model.hasDeck(editedDeck)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        Deck deckToEdit = lastShownList.get(index.getZeroBased());

        model.setDeck(deckToEdit, editedDeck);
        model.moveCards(deckToEdit, editedDeck);

        return new CommandResult(String.format(MESSAGE_EDIT_DECK_SUCCESS,
                index.getOneBased(), editedDeck.getDeckName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDeckCommand)) {
            return false;
        }

        // state check
        EditDeckCommand e = (EditDeckCommand) other;
        return index.equals(e.index) && editedDeck.equals(e.editedDeck);
    }
}
