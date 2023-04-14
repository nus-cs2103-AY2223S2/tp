package seedu.powercards.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.UniqueCardList;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.deck.UniqueDeckList;
import seedu.powercards.model.tag.Tag;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameCard comparison)
 */
public class MasterDeck implements ReadOnlyMasterDeck {

    private final UniqueCardList cards;
    private final UniqueDeckList decks;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        cards = new UniqueCardList();
        decks = new UniqueDeckList();
    }

    public MasterDeck() {}

    /**
     * Creates an Deck using the Cards in the {@code toBeCopied}
     */
    public MasterDeck(ReadOnlyMasterDeck toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the card list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setCards(List<Card> cards) {
        this.cards.setCards(cards);
    }

    public void setDecks(List<Deck> decks) {
        this.decks.setDecks(decks);
    }

    /**
     * Resets the existing data of this {@code Deck} with {@code newData}.
     */
    public void resetData(ReadOnlyMasterDeck newData) {
        requireNonNull(newData);

        setCards(newData.getCardList());
        setDecks(newData.getDeckList());
    }

    //// card-level operations

    /**
     * Returns true if a card with the same identity as {@code card} exists in the master deck.
     */
    public boolean hasCard(Card card) {
        requireNonNull(card);
        return cards.contains(card);
    }

    /**
     * Adds a card to the master deck.
     * The card must not already exist in the master deck.
     */
    public void addCard(Card p) {
        cards.add(p);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the master deck.
     * The card identity of {@code editedCard} must not be the same as another existing card in the master deck.
     */
    public void setCard(Card target, Card editedCard) {
        requireNonNull(editedCard);
        cards.setCard(target, editedCard);
    }

    /**
     * Tags the given card in the list with the new tag.
     *
     * @param target Card to be tagged.
     * @param tag New tag.
     */
    public void tagCard(Card target, Tag tag) {
        setCard(target, target.buildCardWithtag(tag));
    }

    /**
     * Removes {@code key} from this {@code Deck}.
     * {@code key} must exist in the MasterDeck.
     */
    public void removeCard(Card key) {
        cards.remove(key);
    }

    //// util methods

    /**
     * For sample data (SampleDataUtil), Cards may be added without adding Decks,
     * hence this function initialises Decks from the list of Cards.
     * In normal operation, Cards can only be added when a Deck is selected
     * hence this function should not be called in normal operation.
     */
    public void initDecks() {
        cards.asUnmodifiableObservableList().stream()
                .map(Card::getDeck)
                .distinct()
                .forEach(this::addDeck);
    }

    /**
     * Returns true if a deck with the same identity as {@code deck} exists.
     */
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    /**
     * Adds a card to the masterDeck.
     * The card must not already exist in the masterDeck.
     */
    public void addDeck(Deck d) {
        decks.add(d);
    }

    /**
     * Replaces the given deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist.
     * The deck identity of {@code editedCard} must not be the same as another existing deck.
     */
    public void setDeck(Deck target, Deck editedDeck) {
        requireNonNull(editedDeck);
        decks.setDeck(target, editedDeck);
    }

    /**
     * Changes the association of all the cards from the old deck to the new deck.
     *
     * @param oldDeck The deck which cards are currently associated with.
     * @param newDeck The deck which cards are to be associated with.
     */
    public void moveCards(Deck oldDeck, Deck newDeck) {
        for (Card c : cards) {
            if (c.isInDeck(oldDeck)) {
                Card editedCard = new Card(c.getQuestion(), c.getAnswer(), c.getTag(), newDeck);
                setCard(c, editedCard);
            }
        }
    }

    /**
     * Removes {@code key} from this {@code MasterDeck} and its corresponding cards.
     * {@code key} must exist.
     */
    public void removeDeck(Deck key) {
        List<Card> cardsToRemove = cards.asUnmodifiableObservableList().parallelStream()
                .filter(card -> card.isInDeck(key))
                .collect(Collectors.toList());
        cardsToRemove.forEach(this::removeCard);

        decks.remove(key);
    }

    @Override
    public String toString() {
        return cards.asUnmodifiableObservableList().size() + " cards";
        // TODO: refine later
    }

    @Override
    public ObservableList<Card> getCardList() {
        return cards.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Deck> getDeckList() {
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MasterDeck // instanceof handles nulls
                && cards.equals(((MasterDeck) other).cards)
                && decks.equals(((MasterDeck) other).decks));
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }

}
