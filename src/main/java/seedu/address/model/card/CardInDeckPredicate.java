package seedu.address.model.card;

import java.util.function.Predicate;

import seedu.address.model.deck.Deck;

/**
 * Tests that a {@code Card}'s {@code Question} is in the given deck.
 */
public class CardInDeckPredicate implements Predicate<Card> {
    private final Deck deck;

    /**
     * Constructs a CardInDeckPredicate
     * @param deck The Deck in question.
     */
    public CardInDeckPredicate(Deck deck) {
        this.deck = deck;
    }

    @Override
    public boolean test(Card card) {
        return deck.isSameDeck(card.getDeck().get());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CardInDeckPredicate // instanceof handles nulls
                && deck.equals(((CardInDeckPredicate) other).deck)); // state check
    }

}
