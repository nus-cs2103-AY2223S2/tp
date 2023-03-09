package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.card.exceptions.DuplicatePersonException;
import seedu.address.model.card.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A card is considered unique by comparing using {@code Card#isSamePerson(Card)}. As such, adding and updating of
 * persons uses Card#isSamePerson(Card) for equality so as to ensure that the card being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a card uses Card#equals(Object) so
 * as to ensure that the card with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Card#isSameCard(Card)
 */
public class UniquePersonList implements Iterable<Card> {

    private final ObservableList<Card> internalList = FXCollections.observableArrayList();
    private final ObservableList<Card> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent card as the given argument.
     */
    public boolean contains(Card toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCard);
    }

    /**
     * Adds a card to the list.
     * The card must not already exist in the list.
     */
    public void add(Card toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the list.
     * The card identity of {@code editedCard} must not be the same as another existing card in the list.
     */
    public void setPerson(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameCard(editedCard) && contains(editedCard)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedCard);
    }

    /**
     * Removes the equivalent card from the list.
     * The card must exist in the list.
     */
    public void remove(Card toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setPersons(List<Card> cards) {
        requireAllNonNull(cards);
        if (!personsAreUnique(cards)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(cards);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Card> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Card> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code cards} contains only unique cards.
     */
    private boolean personsAreUnique(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).isSameCard(cards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
