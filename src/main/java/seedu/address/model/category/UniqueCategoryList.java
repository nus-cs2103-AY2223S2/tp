package seedu.address.model.category;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.List;

public class UniqueCategoryList implements Iterable<Category> {

    private final ObservableList<Category> internalListOfCategories = FXCollections.observableArrayList();

    private final ObservableList<Category> internalUnmodifiableList = FXCollections.
            unmodifiableObservableList(internalListOfCategories);

    public boolean contains(Category toCheck) {
        requireNonNull(toCheck);

        return internalListOfCategories.stream().anyMatch(toCheck::isSameCategory);
    }

    public void add(Category newCategory) {
        requireNonNull(newCategory);

        if (contains(newCategory)) {
            //Throw an exception here later
        }

        internalListOfCategories.add(newCategory);
    }

    public void remove(Category toRemove) {
        requireNonNull(toRemove);

        if (!internalListOfCategories.remove(toRemove)) {
            //Throw an exception here later
        }
    }

    public void setCategoryList(UniqueCategoryList replacementList) {
        requireNonNull(replacementList);
        internalListOfCategories.setAll(replacementList.internalListOfCategories);
    }

    public void setCategoryList(List<Category> listOfCategories) {
        requireAllNonNull(listOfCategories);
        if (!categoriesAreUnique(listOfCategories)) {
            //Throw an exception here
        }

        internalListOfCategories.setAll(listOfCategories);
    }

    public boolean categoriesAreUnique(List<Category> listOfCategories) {
        for (int i = 0; i < listOfCategories.size(); i++) {
            for (int j = i + 1; j < listOfCategories.size(); j++) {
                if (listOfCategories.get(i).isSameCategory(listOfCategories.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

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
