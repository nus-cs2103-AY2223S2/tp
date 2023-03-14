package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Boolean> scoreList;
    private int currCardNum = 1; // 1-Indexed
    private Card currCard;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck, List<Card> cardList) {
        requireNonNull(deck);
        this.deck = deck;
        this.cardList = cardList;
        //TODO write a shuffle based on user statistics

        // initialise first card
        currCard = this.cardList.get(currCardNum - 1);
        currCard.setAsUnflipped();

        // initialise scoreList
        scoreList = new ArrayList<>(Arrays.asList(new Boolean[this.cardList.size()]));
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
    }

    public Integer getTotalScore() {
        return scoreList.stream().map(bool -> bool ? 1 : 0).mapToInt(a -> a).sum();
    }

    /**
     * Move to the next card.
     */
    public void goToNextCard() {
        currCard.setAsUnflipped(); // always unflip current card before moving to next
        currCardNum++;
        if (currCardNum > cardList.size()) {
            currCardNum--; //TODO throw exception
        } else {
            currCard = cardList.get(currCardNum - 1);
            currCard.setAsUnflipped();
        }
    }

    /**
     * Move back to previous card.
     */
    public void goToPrevCard() {
        currCard.setAsUnflipped();
        currCardNum--;
        if (currCardNum <= 0) {
            currCardNum++; //TODO throw exception
        } else {
            currCard = cardList.get(currCardNum - 1);
        }
    }

    public Card getCurrCard() {
        return currCard;
    }

    public void markCurrCardAsCorrect() {
        scoreList.set(currCardNum - 1, true);
        goToNextCard();
    }

    public void markCurrCardAsWrong() {
        scoreList.set(currCardNum - 1, false);
        goToNextCard();
    }

    public void flipCard() {
        currCard.setAsFlipped();
    }

    public void unflipCard() {
        currCard.setAsUnflipped();
    }

    public boolean isFlipped() {
        return currCard.isFlipped();
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
