package seedu.powercards.model.review;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.ATOM;
import static seedu.powercards.testutil.TypicalCards.LIFE;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.PRESIDENT;
import static seedu.powercards.testutil.TypicalCards.VARIABLE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;

public class ReviewTest {
    private Review review;
    private final Deck deck = new Deck("testDeck");
    private final List<Card> cardsInDeck = new ArrayList<>();
    private final int userSetNum = 5;

    @BeforeEach
    public void setUp() {
        cardsInDeck.add(LOOP);
        cardsInDeck.add(VARIABLE);
        cardsInDeck.add(ATOM);
        cardsInDeck.add(LIFE);
        cardsInDeck.add(PRESIDENT);
        review = new Review(deck, cardsInDeck, userSetNum);
    }

    @Test
    public void constructor_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Review(null, cardsInDeck, userSetNum));
    }

    @Test
    public void constructor_nullCardsInDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Review(deck, null, userSetNum));
    }

    @Test
    public void goToNextCard_lastCard_returnsFalse() {
        for (int i = 0; i < userSetNum - 1; i++) {
            assertTrue(review.goToNextCard());
        }
        assertFalse(review.goToNextCard());
    }

    @Test
    public void goToPrevCard_firstCard_returnsFalse() {
        assertFalse(review.goToPrevCard());
    }

    @Test
    public void isCurrCardFlipped_flippedCard_returnsTrue() {
        review.flipCurrCard();
        assertTrue(review.isCurrCardFlipped());
    }

    @Test
    public void isCurrCardFlipped_unflippedCard_returnsFalse() {
        assertFalse(review.isCurrCardFlipped());
    }

}
