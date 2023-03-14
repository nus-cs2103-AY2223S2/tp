package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DECK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DECKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deck.Deck;
import seedu.address.model.Model;

/**
 * Edits the name of the deck
 */

public class EditDeckCommand extends Command {

    public static final String COMMAND_WORD = "editDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the name of the deck "
            + "by the index number used in the displayed deck list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DECK + "[DECK NAME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DECK + "LAC1201";

    public static final String MESSAGE_EDIT_DECK_SUCCESS = "Edited Deck: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck name already exists.";

    private final Index index;
    private final Deck editedDeck;

    /**
     * @param index      of the deck to edit
     * @param editedDeck
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

        if(index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        if(model.hasDeck(editedDeck)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        Deck deckToEdit = lastShownList.get(index.getZeroBased()); //target
        model.setDeck(deckToEdit, editedDeck);
        model.updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);

        return new CommandResult(String.format(MESSAGE_EDIT_DECK_SUCCESS, editedDeck.getDeckName()));
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
