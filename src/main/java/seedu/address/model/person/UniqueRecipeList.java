package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A recipe is considered unique by comparing using {@code Recipe#isSamePerson(Recipe)}. As such, adding and updating of
 * persons uses Recipe#isSamePerson(Recipe) for equality so as to ensure that the recipe being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a recipe uses Recipe#equals(Object) so
 * as to ensure that the recipe with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Recipe#isSamePerson(Recipe)
 */
public class UniquePersonList implements Iterable<Recipe> {

    private final ObservableList<Recipe> internalList = FXCollections.observableArrayList();
    private final ObservableList<Recipe> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent recipe as the given argument.
     */
    public boolean contains(Recipe toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a recipe to the list.
     * The recipe must not already exist in the list.
     */
    public void add(Recipe toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the recipe {@code target} in the list with {@code editedRecipe}.
     * {@code target} must exist in the list.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the list.
     */
    public void setPerson(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedRecipe) && contains(editedRecipe)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedRecipe);
    }

    /**
     * Removes the equivalent recipe from the list.
     * The recipe must exist in the list.
     */
    public void remove(Recipe toRemove) {
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
     * Replaces the contents of this list with {@code recipes}.
     * {@code recipes} must not contain duplicate recipes.
     */
    public void setPersons(List<Recipe> recipes) {
        requireAllNonNull(recipes);
        if (!personsAreUnique(recipes)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(recipes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Recipe> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Recipe> iterator() {
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
     * Returns true if {@code recipes} contains only unique recipes.
     */
    private boolean personsAreUnique(List<Recipe> recipes) {
        for (int i = 0; i < recipes.size() - 1; i++) {
            for (int j = i + 1; j < recipes.size(); j++) {
                if (recipes.get(i).isSamePerson(recipes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
