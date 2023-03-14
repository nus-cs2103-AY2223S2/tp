package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;

/**
 * Represents a Review session that is currently underway.
 * Guarantees: details are present and not null, field values are validated.
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

    public Card getCurrCard() {
        return currCard;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
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

    /**
     * Returns the sum of the total score on the current scoreList.
     * @return current total score of review.
     */
    public Integer getTotalScore() {
        return scoreList.stream().map(bool -> bool ? 1 : 0).mapToInt(a -> a).sum();
    }

    /**
     * Move to the next card to be under review.
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
     * Move back to previous card to be under review.
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

    /**
     * Marks the current card as correct in the scoreList
     * by setting the respective index in scoreList as true.
     */
    public void markCurrCardAsCorrect() {
        scoreList.set(currCardNum - 1, true);
        goToNextCard();
    }

    /**
     * Marks the current card as wrong in the scoreList
     * by setting the respective index in scoreList as false.
     */
    public void markCurrCardAsWrong() {
        scoreList.set(currCardNum - 1, false);
        goToNextCard();
    }
}
