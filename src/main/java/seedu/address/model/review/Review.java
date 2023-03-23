package seedu.address.model.review;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Represents a Review session that is currently underway.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Review {

    private final Deck deck;
    private final List<Card> cardList;
    private final List<Boolean> scoreList;
    private int currCardNum = 1; // 1-Indexed
    private Card currCard;

    private final int totalNumCards;
    private List<Integer> orderOfCards;
    private ObservableList<Pair<String, String>> reviewStatsList;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck, List<Card> cardList) {
        requireAllNonNull(deck, cardList);

        this.deck = deck;
        this.cardList = cardList;
        totalNumCards = cardList.size();
        unflipAllCards();

        // initialise order of card
        orderOfCards = new Random().ints(0, cardList.size())
                .distinct().limit(totalNumCards).boxed().collect(Collectors.toList());

        // initialise first card
        currCard = this.cardList.get(orderOfCards.get(currCardNum - 1));

        // initialise scoreList
        scoreList = new ArrayList<>(Arrays.asList(new Boolean[totalNumCards]));
        Collections.fill(scoreList, false);

        // initialise reviewStats
        reviewStatsList = FXCollections.observableList(new ArrayList<>());
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor of Review object used if user has set a card limit for each Review session.
     */
    public Review(Deck deck, List<Card> cardList, int userSetNum) {
        requireNonNull(deck);
        requireNonNull(cardList);

        this.deck = deck;
        this.cardList = cardList;
        totalNumCards = userSetNum;
        unflipAllCards();

        // initialise order of card
        if (userSetNum >= cardList.size()) {
            orderOfCards = new Random().ints(0, cardList.size())
                    .distinct().limit(cardList.size()).boxed().collect(Collectors.toList());
            // ensures user sees all cards once, then appends remaining cards at random
            orderOfCards.addAll(new Random().ints(
                            userSetNum - cardList.size(),
                            0,
                            cardList.size() - 1)
                    .boxed().collect(Collectors.toList()));
        } else if (userSetNum < cardList.size()) {
            // all cards seen will be unique when user set number is less than card list size
            orderOfCards = new Random().ints(0, cardList.size())
                    .distinct().limit(userSetNum).boxed().collect(Collectors.toList());
        }

        // initialise first card
        currCard = this.cardList.get(orderOfCards.get(currCardNum - 1));

        // initialise scoreList
        scoreList = new ArrayList<>(Arrays.asList(new Boolean[totalNumCards]));
        Collections.fill(scoreList, false);

        reviewStatsList = FXCollections.observableList(new ArrayList<Pair<String, String>>());
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

    public boolean isFlipped() {
        return currCard.isFlipped();
    }

    /**
     * Flips the current card under review.
     */
    public void flipCard() {
        if (isFlipped()) {
            currCard.setAsUnflipped();
        } else {
            currCard.setAsFlipped();
        }
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
     * @return boolean indicating if card is the last card
     */
    public boolean goToNextCard() {
        boolean priorStateIsFlipped = currCard.isFlipped();
        currCard.setAsUnflipped(); // always unflip current card before moving to next
        currCardNum++;
        if (currCardNum > totalNumCards) {
            currCardNum--;
            if (priorStateIsFlipped) {
                currCard.setAsFlipped();
            }
            updateReviewStatsList();
            return false;
        } else {
            currCard = cardList.get(orderOfCards.get(currCardNum - 1));
            currCard.setAsUnflipped();
            updateReviewStatsList();
            return true;
        }
    }

    /**
     * Move back to previous card to be under review.
     * @return
     */
    public boolean goToPrevCard() {
        currCard.setAsUnflipped(); // always unflip current card before moving to prev
        currCardNum--;
        if (currCardNum <= 0) {
            currCardNum++;
            updateReviewStatsList();
            return false;
        } else {
            currCard = cardList.get(orderOfCards.get(currCardNum - 1));
            currCard.setAsUnflipped();
            updateReviewStatsList();
            return true;
        }
    }

    /**
     * Marks the current card as correct in the scoreList
     * by setting the respective index in scoreList as true.
     */
    public void markCurrCardAsCorrect() {
        scoreList.set(currCardNum - 1, true);
        updateReviewStatsList();
    }

    /**
     * Marks the current card as wrong in the scoreList
     * by setting the respective index in scoreList as false.
     */
    public void markCurrCardAsWrong() {
        scoreList.set(currCardNum - 1, false);
        updateReviewStatsList();
    }

    public void unflipAllCards() {
        cardList.stream().forEach(Card::setAsUnflipped);
    }

    public void flipAllCards() {
        cardList.stream().forEach(Card::setAsFlipped);
    }

    /**
     * Tags current card in review based on enum Hard, Medium, Easy
     * @param tag
     */
    public void tagCurrentCard(Tag tag) {
        currCard.addTag(tag);
    }


    public ObservableList<Pair<String, String>> getReviewStatsList() {
        updateReviewStatsList();
        return reviewStatsList;
    }
    public ObservableList<Pair<String, String> > getReviewDeckNameList() {
        return deck.getDeckNameList();
    }
    private void updateReviewStatsList() {
        Pair<String, String> title = new Pair<>("Deck Name", deck.getDeckName());
        Pair<String, String> cardsSeen = new Pair<>("Current Card Number:",
                String.format("%d/%d", currCardNum, totalNumCards));
        Pair<String, String> currentScore = new Pair<>("Current Score: ",
                 String.format("%d", getTotalScore()));
        Pair<String, String> flip = new Pair<>("Press \\ to flip", "");
        Pair<String, String> next = new Pair<>("Enter [ to go back, ] to go forward", "");
        Pair<String, String> mark = new Pair<>("Press ; to mark wrong, ' to mark correct", "");
        this.reviewStatsList.clear();
        this.reviewStatsList.addAll(title, cardsSeen, currentScore, flip, next, mark);
    }
}
