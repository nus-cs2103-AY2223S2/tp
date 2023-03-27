package seedu.address.model.review;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
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

        // initialise shuffled order of cards and limit
        orderOfCards = new Random().ints(0, cardList.size())
                .distinct().limit(userSetNum).boxed().collect(Collectors.toList());

        // initialise first card
        currCard = this.cardList.get(orderOfCards.get(currCardNum - 1));

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

    public void setCard(Tag tag) {
        currCard = new Card(currCard.getQuestion(), currCard.getAnswer(), tag, currCard.getDeck());
    }

    public void unflipAllCards() {
        cardList.stream().forEach(Card::setAsUnflipped);
    }

    public void flipAllCards() {
        cardList.stream().forEach(Card::setAsFlipped);
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
        return (int) cardList.stream().filter(card -> card.getTagName() == "untagged").count();
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
                String.format("%d/%d", currCardNum, totalNumCards));
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
