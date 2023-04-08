package seedu.address.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.cardcommands.AddCardCommand.MESSAGE_NO_SELECTED_DECK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.deckcommands.ShowDecksCommand;
import seedu.address.model.Model;
import seedu.address.model.card.CardInDeckPredicate;
import seedu.address.model.deck.Deck;

/**
 * Lists all cards in the selected deck to the user.
 */
public class ShowCardsCommand extends Command {

    public static final String COMMAND_WORD = "showCards";

    public static final String MESSAGE_SUCCESS = "Listed all cards in selected deck";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Optional<Deck> selectedDeck = model.getSelectedDeck();
        assert selectedDeck.isPresent() : MESSAGE_NO_SELECTED_DECK;
        model.updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS.and(new CardInDeckPredicate(selectedDeck.get())));
        return new CommandResult(MESSAGE_SUCCESS,
                false, false, false, false, false, false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCardsCommand); // instanceof handles nulls
    }
}
