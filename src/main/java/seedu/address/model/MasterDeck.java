package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.card.Card;
import seedu.address.model.card.UniqueCardList;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.UniqueDeckList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
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
     * Creates an Deck using the Persons in the {@code toBeCopied}
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
     * Returns true if a card with the same identity as {@code card} exists in the address book.
     */
    public boolean hasCard(Card card) {
        requireNonNull(card);
        return cards.contains(card);
    }

    /**
     * Adds a card to the address book.
     * The card must not already exist in the address book.
     */
    public void addCard(Card p) {
        cards.add(p);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the address book.
     * The card identity of {@code editedCard} must not be the same as another existing card in the address book.
     */
    public void setCard(Card target, Card editedCard) {
        requireNonNull(editedCard);

        cards.setCard(target, editedCard);
    }

    /**
     * Removes {@code key} from this {@code Deck}.
     * {@code key} must exist in the address book.
     */
    public void removeCard(Card key) {
        cards.remove(key);
    }

    //// util methods

    public void initDecks() {
        for (Card card: cards) {
            if (!decks.contains(card.getDeck())){
                System.out.println(card.getDeck().getDeckName());
                addDeck(card.getDeck());
            }
        }
    }

    /**
     * Returns true if a deck with the same identity as {@code deck} exists.
     */
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    /**
     * Adds a card to the address book.
     * The card must not already exist in the address book.
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
     * Removes {@code key} from this {@code MasterDeck}.
     * {@code key} must exist.
     */
    public void removeDeck(Deck key) {
        decks.remove(key);
    }

    @Override
    public String toString() {
        return cards.asUnmodifiableObservableList().size() + " persons";
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
                && cards.equals(((MasterDeck) other).cards));
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }

}
