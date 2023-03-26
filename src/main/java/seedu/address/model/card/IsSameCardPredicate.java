package seedu.address.model.card;

import java.util.function.Predicate;

/**
 * Tests that a {@code Card} is the same as the given {@code Card}.
 */
public class IsSameCardPredicate implements Predicate<Card> {
    private final Card card;
    public IsSameCardPredicate(Card card) {
        this.card = card;
    }

    @Override
    public boolean test(Card card) {
        return card.equals(this.card);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsSameCardPredicate // instanceof handles nulls
                && card.equals(((IsSameCardPredicate) other).card)); // state check
    }

}
