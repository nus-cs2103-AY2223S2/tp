package seedu.address.model.review;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.address.model.card.Card;
import seedu.address.model.card.IsSameCardPredicate;
import seedu.address.model.card.UniqueCardList;
import seedu.address.model.deck.Deck;

/**
 * Represents a Review session that is currently underway.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Review {

    private final Deck deck;

    private UniqueCardList reviewCardList;
    private final FilteredList<Card> filteredReviewCardList;
    private final List<Card> cardList;

    private final int totalNumCards;
    private Card currCard;
    private final List<Integer> orderOfCards;
    private int currCardIndex = 0; // 0-Indexed
    private ObservableList<Pair<String, String>> reviewStatsList;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck, List<Card> cardList, int userSetNum) {
        requireAllNonNull(deck, cardList, userSetNum);

        this.deck = deck;
        totalNumCards = userSetNum < 0 ? cardList.size() : userSetNum;

        initReviewCardList(cardList);
        filteredReviewCardList = new FilteredList<>(reviewCardList.asUnmodifiableObservableList());
        this.cardList = reviewCardList.asUnmodifiableObservableList();

        // Randomise order of cards based on the deck size
        orderOfCards = new Random().ints(0, cardList.size())
                .distinct().limit(totalNumCards).boxed().collect(Collectors.toList());

        // initialise first card
        currCard = this.reviewCardList.asUnmodifiableObservableList().get(orderOfCards.get(0));
        filteredReviewCardList.setPredicate(new IsSameCardPredicate(currCard));

        // initialize review stats
        reviewStatsList = FXCollections.observableList(new ArrayList<>());
    }

    /**
     * Initialize all cards as unflipped.
     *
     * @param cardList List of Cards to initialize UniqueCardList with.
     */
    private void initReviewCardList(List<Card> cardList) {
        reviewCardList = new UniqueCardList();
        for (Card c : cardList) {
            reviewCardList.add(c.buildUnflippedCard());
        }
    }

    /**
     * Returns the list of card in this review.
     */
    public ObservableList<Card> getReviewCardList() {
        return filteredReviewCardList;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
    }

    public boolean isCurrCardFlipped() {
        return currCard.isFlipped();
    }

    private void unflipCard(Card card) {
        this.reviewCardList.setCard(card, card.buildUnflippedCard());
    }

    private void flipCard(Card card) {
        this.reviewCardList.setCard(card, card.buildFlippedCard());
    }

    public void flipCurrCard() {
        if (currCard.isFlipped()) {
            unflipCard(currCard);
        } else {
            flipCard(currCard);
        }
    }

    /**
     * Move to the next card to be under review.
     * @return boolean indicating if card is the last card
     */
    public boolean goToNextCard() {
        if (currCardIndex == totalNumCards - 1) {
            return false;
        }

        unflipCard(currCard); // always unflip current card before moving to next
        currCardIndex++;

        currCard = this.reviewCardList.asUnmodifiableObservableList().get(orderOfCards.get(currCardIndex));
        filteredReviewCardList.setPredicate(new IsSameCardPredicate(currCard));

        updateReviewStatsList();
        return true;
    }

    /**
     * Move back to previous card to be under review.
     * @return boolean indicating if card is the first card.
     */
    public boolean goToPrevCard() {
        if (currCardIndex == 0) {
            return false;
        }

        unflipCard(currCard); // always unflip current card before moving to previous one
        currCardIndex--;

        currCard = this.reviewCardList.asUnmodifiableObservableList().get(orderOfCards.get(currCardIndex));
        filteredReviewCardList.setPredicate(new IsSameCardPredicate(currCard));

        updateReviewStatsList();
        return true;
    }

    public ObservableList<Pair<String, String>> getReviewStatsList() {
        updateReviewStatsList();
        return reviewStatsList;
    }

    public int getNoOfEasyTags() {
        return (int) cardList.stream().filter(card -> card.getTagName().equals("easy")).count();
    }

    public int getNoOfMediumTags() {
        return (int) cardList.stream().filter(card -> card.getTagName().equals("medium")).count();
    }

    public int getNoOfHardTags() {
        return (int) cardList.stream().filter(card -> card.getTagName().equals("hard")).count();
    }

    public int getNoOfUntagged() {
        return (int) cardList.stream().filter(card -> card.getTagName().equals("untagged")).count();
    }

    public void tagCard() {
        updateReviewStatsList();
    }

    public ObservableList<Pair<String, String> > getReviewDeckNameList() {
        return deck.getDeckNameList();
    }

    /**
     * Updates the list of review statistics (deck name, number of cards seen and tag count)
     * The review statistics are updated when next, prev and tagging commands are called.
     */
    public void updateReviewStatsList() {
        Pair<String, String> title = new Pair<>("Deck Name", deck.getDeckName());
        Pair<String, String> cardsSeen = new Pair<>("Current Card Number:",
                String.format("%d/%d", currCardIndex + 1, totalNumCards));
        Pair<String, String> tagCount = new Pair<>("Current Tags:",
                 String.format("%d Easy, %d Medium, %d Hard",
                         getNoOfEasyTags(), getNoOfMediumTags(), getNoOfHardTags()));
        Pair<String, String> flip = new Pair<>("Enter p to flip", "");
        Pair<String, String> next = new Pair<>("Enter [ to go back, ] to go forward", "");
        Pair<String, String> tagging = new Pair<>("To tag, enter l for easy, ; for medium and ' for hard", "");
        this.reviewStatsList.clear();
        this.reviewStatsList.addAll(title, cardsSeen, tagCount, flip, next, tagging); // warning being called here
    }
}
