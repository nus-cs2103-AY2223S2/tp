package seedu.powercards.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.logic.commands.cardcommands.AddCardCommand.MESSAGE_NO_SELECTED_DECK;
import static seedu.powercards.model.Model.PREDICATE_SHOW_ALL_CARDS;

import java.util.Optional;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.card.CardInDeckPredicate;
import seedu.powercards.model.deck.Deck;

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
