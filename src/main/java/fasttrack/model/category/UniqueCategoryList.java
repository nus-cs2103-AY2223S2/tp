package fasttrack.model.category;

import static fasttrack.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of categories that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueCategoryList implements Iterable<Category> {

    private final ObservableList<Category> internalListOfCategories = FXCollections.observableArrayList();
    private final ObservableList<Category> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalListOfCategories);

    /**
     * Returns true if the list contains an equivalent category as the given argument.
     * @param category Category to check for
     */
    public boolean contains(Category category) {
        requireNonNull(category);
        return internalListOfCategories.stream().anyMatch(category::equals);
    }

    /**
     * Adds a category to the list.
     * @param newCategory Category to add
     */
    public void add(Category newCategory) {
        requireNonNull(newCategory);

        if (contains(newCategory)) {
            //Throw an exception here later
        }
        internalListOfCategories.add(newCategory);
    }

    /**
     * Replaces the category {@code target} in the list with {@code editedCategory}.
     * {@code target} must exist in the list.
     * @param category Category to remove
     */
    public void remove(Category category) {
        requireNonNull(category);

        if (!internalListOfCategories.remove(category)) {
            //Throw an exception here later
        }
    }

    /**
     * Delete all Category.
     */
    public void clear() {
        internalListOfCategories.clear();
        internalUnmodifiableList.clear();
    }

    /**
     * Replaces the category {@code target} in the list with {@code editedCategory}.
     * {@code target} must exist in the list.
     * The category identity of {@code editedCategory} must not be
     * the same as another existing category in the list.
     * @param replacementList List of categories to replace the current list
     */
    public void setCategoryList(UniqueCategoryList replacementList) {
        requireNonNull(replacementList);
        internalListOfCategories.setAll(replacementList.internalListOfCategories);
    }

    /**
     * Replaces the category {@code target} in the list with {@code editedCategory}.
     * {@code target} must exist in the list.
     * The category identity of {@code editedCategory} must not be
     * the same as another existing category in the list.
     * @param listOfCategories List of categories to replace the current list
     */
    public void setCategoryList(List<Category> listOfCategories) {
        requireAllNonNull(listOfCategories);
        if (!categoriesAreUnique(listOfCategories)) {
            //Throw an exception here
        }
        internalListOfCategories.setAll(listOfCategories);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * This list will not contain any null categories.
     * @param listOfCategories List of categories to check for uniqueness
     */
    public boolean categoriesAreUnique(List<Category> listOfCategories) {
        for (int i = 0; i < listOfCategories.size(); i++) {
            for (int j = i + 1; j < listOfCategories.size(); j++) {
                if (listOfCategories.get(i).equals(listOfCategories.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * This list will not contain any null categories.
     */
    public ObservableList<Category> asUnmodifiableList() {
        return this.internalUnmodifiableList;
    }

    @Override
    public Iterator<Category> iterator() {
        return this.internalListOfCategories.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UniqueCategoryList)) {
            return false;
        }
        UniqueCategoryList otherInUniqueList = (UniqueCategoryList) other;
        return this.internalListOfCategories.equals(otherInUniqueList.internalListOfCategories);
    }

    @Override
    public int hashCode() {
        return internalListOfCategories.hashCode();
    }
}
