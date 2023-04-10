package fasttrack.ui;

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of categories.
 */
public class CategoryListPanel extends UiPart<Region> {
    private static final String FXML = "CategoryListPanel.fxml";
    private final ObservableList<Expense> expenseObservableList;

    @FXML
    private ListView<Category> categoryListView;

    /**
     * Creates a {@code CategoryListPanel} with the given {@code ObservableList}.
     */
    public CategoryListPanel(ObservableList<Category> categoryList, ObservableList<Expense> expenseList) {
        super(FXML);
        this.expenseObservableList = expenseList;
        categoryListView.setItems(categoryList);
        categoryListView.setCellFactory(listView -> new CategoryListViewCell());
    }

    private int getAssociatedExpenseCount(Category category) {
        return (int) expenseObservableList.stream()
                .filter(e -> e.getCategory().equals(category))
                .count();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Category} using a {@code CategoryCard}.
     */
    class CategoryListViewCell extends ListCell<Category> {
        @Override
        protected void updateItem(Category category, boolean empty) {
            super.updateItem(category, empty);

            if (empty || category == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CategoryCard(category, getIndex() + 1, getAssociatedExpenseCount(category)).getRoot());
            }
        }
    }

}
