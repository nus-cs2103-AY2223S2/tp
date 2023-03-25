package seedu.address.model.card;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Represents a Card in the master deck.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Card {

    // Identity fields
    private final Question question;
    private final Answer answer;
    private final Deck deck;


    // Data fields
    private final Tag tag;
    private boolean isFlipped = true;

    /**
     * Every field must be present and not null.
     */
    public Card(Question question, Answer answer, Tag tag, Deck deck) {
        requireAllNonNull(question, answer, deck);
        this.question = question;
        this.answer = answer;
        this.tag = tag;
        this.deck = deck;
    }

    /**
     * When tag is not given by user.
     */
    public Card(Question question, Answer answer, Deck deck) {
        requireAllNonNull(question, answer, deck);
        this.question = question;
        this.answer = answer;
        this.tag = new Tag("untagged");
        this.deck = deck;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    public String getTagName() {
        if (tag == null) {
            return null;
        }
        return tag.tagName.name().toLowerCase();
    }

    public Deck getDeck() {
        return deck;
    }

    /**
     * Returns true if both cards have the same question.
     * This defines a weaker notion of equality between two cards.
     */
    public boolean isSameCard(Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard != null
                && otherCard.getQuestion().equals(getQuestion())
                && otherCard.getAnswer().equals(getAnswer())
                && otherCard.getDeck().equals(getDeck());
    }

    /**
     * Checks if the card belongs to a given deck.
     *
     * @param deck The deck to check if card belongs to.
     * @return true if card belongs to the deck.
     */
    public boolean isInDeck(Deck deck) {
        return this.deck.equals(deck);
    }

    /* ============================== FOR REVIEW FUNCTIONS ============================== */

    /**
     * Returns true if the card is flipped.
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Sets card as flipped.
     */
    public void setAsFlipped() {
        this.isFlipped = true;
    }

    /**
     * Sets card as unflipped.
     */
    public void setAsUnflipped() {
        this.isFlipped = false;
    }

    /**
     * Returns true if both cards have the same identity and data fields.
     * This defines a stronger notion of equality between two cards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Card)) {
            return false;
        }

        Card otherCard = (Card) other;
        return otherCard.getQuestion().equals(getQuestion())
                && otherCard.getAnswer().equals(getAnswer())
                && otherCard.getTag().equals(getTag())
                && otherCard.getDeck().equals(getDeck());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer, tag, deck);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getQuestion())
                .append("; Answer: ")
                .append(getAnswer());

        if (getTag() != null) {
            builder.append(getTag());
        }

        return builder.toString();
    }

}
