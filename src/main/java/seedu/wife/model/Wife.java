package seedu.wife.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.UniqueFoodList;

/**
 * Wraps all data at the WIFE level
 * Duplicates are not allowed (by .isSameFood comparison)
 */
public class Wife implements ReadOnlyWife {

    private final UniqueFoodList foods;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foods = new UniqueFoodList();
    }

    public Wife() {}

    /**
     * Creates WIFE using the Foods in the {@code toBeCopied}
     */
    public Wife(ReadOnlyWife toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the food list with {@code persons}.
     * {@code persons} must not contain duplicate foods.
     */
    public void setFoods(List<Food> foods) {
        this.foods.setFoods(foods);
    }

    /**
     * Resets the existing data of this {@code WIFE} with {@code newData}.
     */
    public void resetData(ReadOnlyWife newData) {
        requireNonNull(newData);

        setFoods(newData.getFoodList());
    }

    //// person-level operations

    /**
     * Returns true if food with the same name and expiry date as another {@code food} exists in the WIFE.
     */
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foods.contains(food);
    }

    /**
     * Adds food to WIFE.
     * The food must not already exist in WIFE.
     */
    public void addFood(Food p) {
        foods.add(p);
    }

    /**
     * Replaces the given food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in WIFE.
     * The food of {@code editedFood} must not be the same as another existing food in the address book.
     */
    public void setFood(Food target, Food editedFood) {
        requireNonNull(editedFood);

        foods.setFood(target, editedFood);
    }

    /**
     * Removes {@code key} from {@code WIFE}.
     * {@code key} must exist in WIFE.
     */
    public void removeFood(Food key) {
        foods.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return foods.asUnmodifiableObservableList().size() + " foods";
        // TODO: refine later
    }

    @Override
    public ObservableList<Food> getFoodList() {
        return foods.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Wife // instanceof handles nulls
                && foods.equals(((Wife) other).foods));
    }

    @Override
    public int hashCode() {
        return foods.hashCode();
    }
}
