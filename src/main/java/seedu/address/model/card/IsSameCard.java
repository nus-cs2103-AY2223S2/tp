package seedu.address.model.card;

import java.util.function.Predicate;

/**
 * Tests that a {@code Card} is the same as the given {@code Card}.
 */
public class IsSameCard implements Predicate<Card> {
    private final Card card;
    public IsSameCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean test(Card card) {
        return card.equals(this.card);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsSameCard // instanceof handles nulls
                && card.equals(((IsSameCard) other).card)); // state check
    }

}
