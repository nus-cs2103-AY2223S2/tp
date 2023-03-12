package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Represents a Card in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Review {

    private final Deck deck;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck) {
        requireNonNull(deck);
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
    }

//    @Override
//    public String toString() {
//        final StringBuilder builder = new StringBuilder();
//        builder.append(getQuestion())
//                .append("; Answer: ")
//                .append(getAddress());
//
//        Set<Tag> tags = getTags();
//        if (!tags.isEmpty()) {
//            builder.append("; Tags: ");
//            tags.forEach(builder::append);
//        }
//        return builder.toString();
//    }
}
