package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.category.Category;
public interface ReadOnlyCategoryList {

    ObservableList<Category> getCategoryList();
}
