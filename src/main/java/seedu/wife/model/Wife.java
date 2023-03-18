package seedu.wife.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.UniqueFoodList;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.UniqueTagList;

/**
 * Wraps all data at the WIFE level
 * Duplicates are not allowed (by .isSameFood comparison)
 */
public class Wife implements ReadOnlyWife {

    private final UniqueFoodList foods;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foods = new UniqueFoodList();
        tags = new UniqueTagList();
    }

    public Wife() {}

    /**
     * Creates WIFE using the Foods and Tags in the {@code toBeCopied}
     */
    public Wife(ReadOnlyWife toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the food list with {@code foods}.
     * {@code foods} must not contain duplicate foods.
     */
    public void setFoods(List<Food> foods) {
        this.foods.setFoods(foods);
    }

    /**
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> foods) {
        this.tags.setTags(foods);
    }

    /**
     * Resets the existing data of this {@code WIFE} with {@code newData}.
     */
    public void resetData(ReadOnlyWife newData) {
        requireNonNull(newData);

        setFoods(newData.getFoodList());
        setTags(newData.getTagList());
    }

    //// tag-level operations

    /**
     * Returns true if tag with the same name and expiry date as another {@code tag} exists in the WIFE.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds tag to WIFE.
     * The tag must not already exist in WIFE.
     */
    public void addTag(Tag t) {
        tags.add(t);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in WIFE.
     * The tag of {@code editedTag} must not be the same as another existing tag in WIFE.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tags.setTag(target, editedTag);
    }

    /**
     * Removes {@code key} from {@code WIFE}.
     * {@code key} must exist in WIFE.
     */
    public void removeTag(Tag key) {
        tags.remove(key);
    }

    //// food-level operations

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
     * The food of {@code editedFood} must not be the same as another existing food in the WIFE.
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
        return foods.asUnmodifiableObservableList().size()
            + " foods \n"
            + tags.asUnmodifiableObservableList().size() + " available tags";
    }

    @Override
    public ObservableList<Food> getFoodList() {
        return foods.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (
                    other instanceof Wife // instanceof handles nulls
                    && foods.equals(
                        ((Wife) other).foods
                        )
                    && tags.equals(
                        ((Wife) other).tags
                    )
                );
    }

    @Override
    public int hashCode() {
        return foods.hashCode() + tags.hashCode();
    }
}
