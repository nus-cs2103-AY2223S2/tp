package seedu.powercards.model.card;

import java.util.List;
import java.util.function.Predicate;

import seedu.powercards.commons.util.StringUtil;

/**
 * Tests that a {@code Card}'s {@code Question} matches any of the keywords given.
 */
public class QuestionContainsKeywordsPredicate implements Predicate<Card> {
    private final List<String> keywords;

    public QuestionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Card card) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(card.getQuestion().question, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuestionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((QuestionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
