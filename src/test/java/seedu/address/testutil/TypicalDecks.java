package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.deck.Deck;

/**
 * A utility class containing a list of {@code Deck} objects to be used in tests.
 */
public class TypicalDecks {
    public static final Deck VALID_DECK_HISTORY = new Deck("History");
    public static final Deck VALID_DECK_SCIENCE = new Deck("Science");

    private TypicalDecks() {} // prevents instantiation

    public static List<Deck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(VALID_DECK_HISTORY, VALID_DECK_SCIENCE));
    }
}
