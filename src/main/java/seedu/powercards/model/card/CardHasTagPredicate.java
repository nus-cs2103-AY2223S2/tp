package seedu.powercards.model.card;

import java.util.List;
import java.util.function.Predicate;

import seedu.powercards.model.tag.Tag.TagName;

/**
 * Tests that a {@code Card} has any of the given tags.
 */
public class CardHasTagPredicate implements Predicate<Card> {
    private final List<TagName> difficulties;

    /**
     * Constructs a CardHasTagPredicate
     * @param difficulties List of difficulty tags.
     */
    public CardHasTagPredicate(List<TagName> difficulties) {
        this.difficulties = difficulties;
    }

    @Override
    public boolean test(Card card) {
        if (difficulties.isEmpty()) {
            return true;
        } else {
            return difficulties.contains(card.getTag().tagName);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CardHasTagPredicate // instanceof handles nulls
                && difficulties.equals(((CardHasTagPredicate) other).difficulties)); // state check
    }

}
