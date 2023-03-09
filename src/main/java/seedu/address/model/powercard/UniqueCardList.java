package seedu.address.model.powercard;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.card.exceptions.DuplicatePersonException;
import seedu.address.model.card.exceptions.PersonNotFoundException;

/**
 * A list of cards that enforces uniqueness between its elements and does not allow nulls.
 * A card is considered unique by comparing using {@code PowerCard#isSamePowerCard(PowerCard)}. As such, adding and
 * updating of cards uses PowerCard#isSamePowerCard(PowerCard) for equality to ensure that the card being added or
 * updated is unique in terms of identity in the UniquePersonList.
 *
 * However, the removal of a card uses PowerCard#equals(Object) to ensure that the card with exactly the same fields
 * will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see PowerCard#isSamePowercard(PowerCard)
 */
public class UniqueCardList implements Iterable<PowerCard> {
    private final ObservableList<PowerCard> internalList = FXCollections.observableArrayList();
    private final ObservableList<PowerCard> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent card as the given argument.
     */
    public boolean contains(PowerCard toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePowercard);
    }

    /**
     * Adds a card to the list.
     * The card must not already exist in the list.
     */
    public void add(PowerCard toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException(); // TODO: change to DuplicateCardException
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent card from the list.
     * The card must exist in the list.
     */
    public void remove(PowerCard toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException(); // TODO change to CardNotFoundException
        }
    }

    /**
     * Replaces the card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the list.
     * The card identity of {@code editedPerson} must not be the same as another existing card in the list.
     */
    public void setCard(PowerCard target, PowerCard editedCard) {
        requireAllNonNull(target, editedCard);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException(); // TODO: Refactor exception
        }

        if (!target.isSamePowercard(editedCard) && contains(editedCard)) {
            throw new DuplicatePersonException(); // TODO: Refactor
        }

        internalList.set(index, editedCard);
    }

    public void setCards(UniqueCardList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setCards(List<PowerCard> cards) {
        requireAllNonNull(cards);
        if (!cardsAreUnique(cards)) {
            throw new DuplicatePersonException(); // TODO: Refactor
        }

        internalList.setAll(cards);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PowerCard> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PowerCard> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCardList // instanceof handles nulls
                && internalList.equals(((UniqueCardList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code cards} contains only unique cards.
     */
    private boolean cardsAreUnique(List<PowerCard> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).isSamePowercard(cards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
