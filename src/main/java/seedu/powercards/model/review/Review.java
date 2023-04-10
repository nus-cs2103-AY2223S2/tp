package seedu.powercards.model.review;

import static seedu.powercards.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.IsSameCardPredicate;
import seedu.powercards.model.card.UniqueCardList;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;

/**
 * Represents a Review session that is currently underway.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Review {

    private final Deck deck;

    private UniqueCardList uniqueReviewCardList;
    private final ObservableList<Card> unmodifiableReviewCardList;
    private final FilteredList<Card> filteredReviewCardList;

    private final int totalNumCards;
    private Card currCard;
    private final List<Integer> orderOfCards;
    private int currCardIndex = 0; // 0-Indexed
    private ObservableList<Pair<String, String>> reviewStatsList;

    /**
     * Every field must be present and not null.
     */
    public Review(Deck deck, List<Card> cardsInDeck, int userSetNum) {
        requireAllNonNull(deck, cardsInDeck, userSetNum);

        this.deck = deck;
        totalNumCards = userSetNum < 0
                ? cardsInDeck.size()
                : Integer.min(userSetNum, cardsInDeck.size());

        // Initialize the unique card list
        initReviewCardList(cardsInDeck);
        this.unmodifiableReviewCardList = uniqueReviewCardList.asUnmodifiableObservableList();
        filteredReviewCardList = new FilteredList<>(this.unmodifiableReviewCardList);

        // Randomise order of cards based on the total number of cards allowed in review
        orderOfCards = new Random().ints(0, cardsInDeck.size())
                .distinct().limit(totalNumCards).boxed().collect(Collectors.toList());

        // initialise first card
        currCard = this.unmodifiableReviewCardList.get(orderOfCards.get(currCardIndex));
        filteredReviewCardList.setPredicate(new IsSameCardPredicate(currCard));

        // initialize review stats
        reviewStatsList = FXCollections.observableList(new ArrayList<>());
        updateReviewStatsList();
    }

    /**
     * Initialize all cards as unflipped in an UniqueCardList instance.
     *
     * @param cardList List of Cards to initialize UniqueCardList with.
     */
    private void initReviewCardList(List<Card> cardList) {
        uniqueReviewCardList = new UniqueCardList();
        for (Card c : cardList) {
            uniqueReviewCardList.add(c.buildUnflippedCard());
        }
    }

    /**
     * Returns the list of card in this review.
     */
    public ObservableList<Card> getFilteredReviewCardList() {
        return filteredReviewCardList;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getDeckName() {
        return deck.getDeckName();
    }

    private void unflipCard(Card card) {
        this.uniqueReviewCardList.setCard(card, card.buildUnflippedCard());
    }

    private void flipCard(Card card) {
        this.uniqueReviewCardList.setCard(card, card.buildFlippedCard());
    }

    /**
     * Updates the current card whenever the current card is modified in the Review card list.
     */
    private void updateCurrCard() {
        int indexInReview = orderOfCards.get(currCardIndex);
        currCard = this.unmodifiableReviewCardList.get(indexInReview);
    }

    /**
     * Flips the current card in review.
     */
    public void flipCurrCard() {
        if (currCard.isFlipped()) {
            unflipCard(currCard);
        } else {
            flipCard(currCard);
        }

        updateCurrCard();
    }

    /**
     * Checks the flip state of the current card in Review.
     *
     * @return true if the current card is flipped, otherwise false
     */
    public boolean isCurrCardFlipped() {
        return currCard.isFlipped();
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
        currCard = unmodifiableReviewCardList.get(orderOfCards.get(currCardIndex));
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
        currCard = unmodifiableReviewCardList.get(orderOfCards.get(currCardIndex));
        filteredReviewCardList.setPredicate(new IsSameCardPredicate(currCard));

        updateReviewStatsList();
        return true;
    }

    /**
     * Returns the current card in the review.
     * Card is always flipped to be consistent with the cards in MasterDeck.
     *
     * @return the current card flipped.
     */
    public Card getCurrCard() {
        return currCard.buildFlippedCard();
    }

    /**
     * Tags the current card in review as easy/medium/hard
     *
     * @param tag the tag to add to current card.
     */
    public void tagCurrentCard(Tag tag) {
        uniqueReviewCardList.setCard(currCard, currCard.buildCardWithtag(tag));
        updateCurrCard();
        updateReviewStatsList();
    }

    public ObservableList<Pair<String, String>> getReviewStatsList() {
        return reviewStatsList;
    }

    public int getNoOfEasyTags() {
        return (int) orderOfCards.stream()
                .map(unmodifiableReviewCardList::get)
                .filter(card -> card.getTagName().equals("easy")).count();

    }

    public int getNoOfMediumTags() {
        return (int) orderOfCards.stream()
                .map(unmodifiableReviewCardList::get)
                .filter(card -> card.getTagName().equals("medium")).count();
    }

    public int getNoOfHardTags() {
        return (int) orderOfCards.stream()
                .map(unmodifiableReviewCardList::get)
                .filter(card -> card.getTagName().equals("hard")).count();
    }

    public int getNoOfUntagged() {
        return (int) unmodifiableReviewCardList.stream().filter(card -> card.getTagName().equals("untagged")).count();
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
        Pair<String, String> navGuide = new Pair<>("", "");
        this.reviewStatsList.clear();
        this.reviewStatsList.addAll(title, cardsSeen, tagCount, navGuide); // warning being called here
    }
}
