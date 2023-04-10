package seedu.powercards.model.deck;

import java.util.List;
import java.util.function.Predicate;

import seedu.powercards.commons.util.StringUtil;

/**
 * Tests that a {@code Card}'s {@code Question} matches any of the keywords given.
 */
public class DeckContainsKeywordsPredicate implements Predicate<Deck> {
    private final List<String> keywords;

    public DeckContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Deck deck) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(deck.getDeckName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeckContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DeckContainsKeywordsPredicate) other).keywords)); // state check
    }

}
