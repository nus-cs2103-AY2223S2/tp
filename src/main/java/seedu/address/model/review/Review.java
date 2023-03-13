package seedu.address.model.review;

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
    private int currScore = 0;
    private int currCardNum = 0;
    private Card currCard;
    private boolean isFlipped = false;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck, List<Card> cardList) {
        requireNonNull(deck);
        this.deck = deck;

        this.cardList = cardList;
        //TODO write a shuffle based on user statistics
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
    }

    public Integer getCurrScore() {
        return currScore;
    }

    public void incrementCurrentScore() {
        currScore++;
    }

    /**
     * Move to the next card.
     */
    public void goToNextCard() {
        isFlipped = false;
        currCardNum++;
        if (currCardNum > cardList.size()) {
            currCardNum--; //TODO throw exception
        } else {
            this.currCard = cardList.get(currCardNum - 1);
        }
    }

    /**
     * Move back to previous card.
     */
    public void goToPrevCard() {
        isFlipped = false;
        currCardNum--;
        if (currCardNum <= 0) {
            currCardNum++; //TODO throw exception
        } else {
            this.currCard = cardList.get(currCardNum - 1);
        }
    }

    public Card getCurrCard() {
        return currCard;
    }

    public void markCurrCardAsCorrect() {
        currScore++; //TODO implement tracking of individual card correctness.
    }

    public void flipCard() {
        isFlipped = true;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Returns the string to be displayed of the current card
     * depending on if the card is flipped
     * @return Question or (Question and Answer) of current card.
     */
    public String displayOnCard() {
        if (isFlipped) {
            return currCard.getQuestion() + "\n" + currCard.getAnswer();
        } else {
            return currCard.getQuestion().toString();
        }
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
