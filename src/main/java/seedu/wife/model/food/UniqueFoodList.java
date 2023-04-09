package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.wife.model.food.exceptions.DuplicateFoodException;
import seedu.wife.model.food.exceptions.FoodNotFoundException;

/**
 * A list of food that enforces uniqueness between its elements and does not allow nulls.
 * A food is considered unique by comparing using {@code Food#isSameFood(Food)}. As such, adding and updating of
 * foods uses Food#isSameFood(Food) for equality so as to ensure that the food being added or updated is
 * unique in terms of identity in the UniqueFoodList. However, the removal of a person uses Food#equals(Object) so
 * as to ensure that the food with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Food#isSameFood(Food)
 */
public class UniqueFoodList implements Iterable<Food> {

    private final ObservableList<Food> internalList = FXCollections.observableArrayList();
    private final ObservableList<Food> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent food as the given argument.
     */
    public boolean contains(Food toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFood);
    }

    /**
     * Adds a food item to the food list.
     * The food must not already exist in the food list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFoodException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the food {@code target} in the list with {@code editedFood}.
     *
     * {@code target} must exist in the list.
     * The food of {@code editedFood} must not be the same as another existing food in the list.
     */
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);

        if (index == -1) {
            throw new FoodNotFoundException();
        }

        if (!target.isSameFood(editedFood) && contains(editedFood)) {
            throw new DuplicateFoodException();
        }

        internalList.set(index, editedFood);
    }

    /**
     * Removes the equivalent food from the list.
     * The food must exist in the list.
     */
    public void remove(Food toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FoodNotFoundException();
        }
    }

    /**
     * Views the equivalent food from the list.
     * The food must exist in the list.
     */
    public void view(Food toView) {
        requireNonNull(toView);
        if (!internalList.contains(toView)) {
            throw new FoodNotFoundException();
        }
    }

    /**
     * Replaces the old food list with a new food list.
     *
     * @param replacement The new food list for replacement.
     */
    public void setFoods(UniqueFoodList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code foods}.
     * {@code foods} must not contain duplicate food.
     */
    public void setFoods(List<Food> foods) {
        requireAllNonNull(foods);
        if (!foodsAreUnique(foods)) {
            throw new DuplicateFoodException();
        }

        internalList.setAll(foods);
    }

    /**
     * Sorts food items of this list with {@code Comparator}.
     */
    public void sort(Comparator<Food> cmp) {
        internalList.sort(cmp);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Food> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Food> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFoodList // instanceof handles nulls
                && internalList.equals(((UniqueFoodList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code foods} contains only unique foods.
     */
    private boolean foodsAreUnique(List<Food> foods) {
        for (int i = 0; i < foods.size() - 1; i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).isSameFood(foods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
