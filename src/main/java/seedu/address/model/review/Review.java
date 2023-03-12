package seedu.address.model.review;

import static java.util.Collections.shuffle;
import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;

/**
 * Represents a Card in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Review {

    private final Deck deck;
    private List<Card> cardList;
    private Integer currentScore = 0;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck, List<Card> cardList) {
        requireNonNull(deck);
        this.deck = deck;

        shuffle(cardList);
        this.cardList = cardList;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void incrementCurrentScore() {
        currentScore++;
    }

    /*
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getQuestion())
                .append("; Answer: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
     */
}
